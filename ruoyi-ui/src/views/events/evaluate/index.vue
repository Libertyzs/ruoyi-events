<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学校" prop="school">
        <el-input
          v-model="queryParams.school"
          placeholder="请输入学校"
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
      <el-form-item label="评委姓名" prop="judgeName">
        <el-input
          v-model="queryParams.judgeName"
          placeholder="请输入评委姓名"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['events:evaluate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['events:evaluate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['events:evaluate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['events:evaluate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="evaluateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="作品ID" align="center" prop="workId" />
      <el-table-column label="学校" align="center" prop="school" />
      <el-table-column label="作品名称" align="center" prop="workName" />
      <el-table-column label="评委姓名" align="center" prop="judgeName" />
      <el-table-column label="得分" align="center" prop="score" />
      <el-table-column label="评价内容" align="center" prop="evaluationContent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['events:evaluate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['events:evaluate:remove']"
          >删除</el-button>
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

    <!-- 添加或修改评价管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作品ID" prop="workId">
          <el-input v-model="form.workId" placeholder="请输入作品ID" />
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-input v-model="form.school" placeholder="请输入学校" />
        </el-form-item>
        <el-form-item label="作品名称" prop="workName">
          <el-input v-model="form.workName" placeholder="请输入作品名称" />
        </el-form-item>
        <el-form-item label="评委姓名" prop="judgeName">
          <el-input v-model="form.judgeName" placeholder="请输入评委姓名" />
        </el-form-item>
        <el-form-item label="得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入得分" />
        </el-form-item>
        <el-form-item label="评价内容">
          <editor v-model="form.evaluationContent" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEvaluate, getEvaluate, delEvaluate, addEvaluate, updateEvaluate } from "@/api/events/evaluate";

export default {
  name: "Evaluate",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 评价管理表格数据
      evaluateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workId: null,
        school: null,
        workName: null,
        judgeName: null,
        score: null,
        evaluationContent: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        workId: [
          { required: true, message: "作品ID不能为空", trigger: "blur" }
        ],
        school: [
          { required: true, message: "学校不能为空", trigger: "blur" }
        ],
        workName: [
          { required: true, message: "作品名称不能为空", trigger: "blur" }
        ],
        judgeName: [
          { required: true, message: "评委姓名不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "得分不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询评价管理列表 */
    getList() {
      this.loading = true;
      listEvaluate(this.queryParams).then(response => {
        this.evaluateList = response.rows;
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
        evaluateId: null,
        workId: null,
        school: null,
        workName: null,
        judgeName: null,
        score: null,
        evaluationContent: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.evaluateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加评价管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const evaluateId = row.evaluateId || this.ids
      getEvaluate(evaluateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改评价管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.evaluateId != null) {
            updateEvaluate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvaluate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const evaluateIds = row.evaluateId || this.ids;
      this.$modal.confirm('是否确认删除评价管理编号为"' + evaluateIds + '"的数据项？').then(function() {
        return delEvaluate(evaluateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('events/evaluate/export', {
        ...this.queryParams
      }, `evaluate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
