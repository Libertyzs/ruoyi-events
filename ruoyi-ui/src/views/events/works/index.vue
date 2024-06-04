<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学校" prop="workSchool">
        <el-input
          v-model="queryParams.workSchool"
          placeholder="请输入学校"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名组别" prop="workGroup">
        <el-input
          v-model="queryParams.workGroup"
          placeholder="请输入报名组别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作品名称" prop="workName">
        <el-input
          v-model="queryParams.workName"
          placeholder="请输入作品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['events:works:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="worksList">
      <el-table-column label="作品ID" align="center" prop="workId" />
      <el-table-column label="参赛账号ID" align="center" prop="accountId" />
      <el-table-column label="学校" align="center" prop="workSchool" />
      <el-table-column label="报名组别" align="center" prop="workGroup" />
      <el-table-column label="作品名称" align="center" prop="workName" />
      <el-table-column label="参赛姓名" align="center" prop="workAuthor" />
      <el-table-column label="联系方式" align="center" prop="contactInfo" />
      <el-table-column label="实施平均分" align="center" prop="averageScore" />
      <el-table-column label="提交时间" align="center" prop="submissionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submissionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-search"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['events:works:edit']"
          >详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 查询作品详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="作品ID">
              <el-input v-model="form.workId" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="参赛账号ID">
              <el-input v-model="form.accountId" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="学校">
              <el-input v-model="form.workSchool" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报名组别">
              <el-input v-model="form.workGroup" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="作品名称">
              <el-input v-model="form.workName" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="参赛姓名">
              <el-input v-model="form.workAuthor" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系方式">
              <el-input v-model="form.contactInfo" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="实施平均分">
              <el-input v-model="form.averageScore" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="提交时间">
              <el-input v-model="form.submissionTime" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="作品详情">
              <el-input type="textarea" v-model="form.workDetails" disabled />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWorks, getWorks } from "@/api/events/works";

export default {
  name: "Works",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作品管理表格数据
      worksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        accountId: null,
        workSchool: null,
        workGroup: null,
        workName: null,
        workAuthor: null,
        workDetails: null,
        contactInfo: null,
        averageScore: null,
        submissionTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        accountId: [
          { required: true, message: "参赛账号ID不能为空", trigger: "blur" }
        ],
        workSchool: [
          { required: true, message: "学校不能为空", trigger: "blur" }
        ],
        workGroup: [
          { required: true, message: "报名组别不能为空", trigger: "blur" }
        ],
        workName: [
          { required: true, message: "作品名称不能为空", trigger: "blur" }
        ],
        workAuthor: [
          { required: true, message: "参赛姓名不能为空", trigger: "blur" }
        ],
        contactInfo: [
          { required: true, message: "联系方式不能为空", trigger: "blur" }
        ],
        submissionTime: [
          { required: true, message: "提交时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询作品管理列表 */
    getList() {
      this.loading = true;
      listWorks(this.queryParams).then(response => {
        this.worksList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        workId: null,
        accountId: null,
        workSchool: null,
        workGroup: null,
        workName: null,
        workAuthor: null,
        workDetails: null,
        contactInfo: null,
        averageScore: null,
        submissionTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 查询按钮操作 */
    handleUpdate(row) {
      this.reset();
      const workId = row.workId;
      getWorks(workId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查询作品详情";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('events/works/export', {
        ...this.queryParams
      }, `works_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
