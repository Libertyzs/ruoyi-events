<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <!-- 身份选择框 -->
      <el-form-item label="身份" prop="roleId">
        <el-select v-model="queryParams.roleId" placeholder="请选择身份" clearable>
          <el-option label="教师" value="1"></el-option>
          <el-option label="评委" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学校" prop="accountSchool">
        <el-input
          v-model="queryParams.accountSchool"
          placeholder="请输入学校"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- 报名组别选择框 -->
      <el-form-item label="报名组别" prop="accountGroup">
        <el-select
          v-model="queryParams.accountGroup"
          placeholder="请选择报名组别"
          clearable
          @change="handleQuery"
        >
          <el-option label="思政课程组" value="思政课程组"></el-option>
          <el-option label="公共基础课程组" value="公共基础课程组"></el-option>
          <el-option label="专业技能课程一组" value="专业技能课程一组"></el-option>
          <el-option label="专业技能课程二组" value="专业技能课程二组"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="账号昵称" prop="accountNick">
        <el-input
          v-model="queryParams.accountNick"
          placeholder="请输入账号昵称"
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
          v-hasPermi="['events:account:add']"
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
          v-hasPermi="['events:account:edit']"
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
          v-hasPermi="['events:account:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['events:account:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="身份" align="center" prop="roleName"/>
      <el-table-column label="学校" align="center" prop="accountSchool" />
      <el-table-column label="姓名" align="center" prop="accountName" />
      <el-table-column label="报名组别" align="center" prop="accountGroup" />
      <el-table-column label="账号昵称" align="center" prop="accountNick" />
      <el-table-column label="账号密码" align="center" prop="accountPassword" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['events:account:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['events:account:remove']"
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

    <!-- 添加或修改账号管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学校" prop="accountSchool">
          <el-input v-model="form.accountSchool" placeholder="请输入学校" />
        </el-form-item>
        <el-form-item label="身份" prop="roleId">
          <el-select v-model="form.roleName" placeholder="请选择身份">
            <el-option label="教师" value="教师"></el-option>
            <el-option label="评委" value="评委"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名" prop="accountName">
          <el-input v-model="form.accountName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="报名组别" prop="accountGroup">
          <el-select v-model="form.accountGroup" placeholder="请选择报名组别">
            <el-option label="思政课程组" value="思政课程组"></el-option>
            <el-option label="公共基础课程组" value="公共基础课程组"></el-option>
            <el-option label="专业技能课程一组" value="专业技能课程一组"></el-option>
            <el-option label="专业技能课程二组" value="专业技能课程二组"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号昵称" prop="accountNick">
          <el-input v-model="form.accountNick" placeholder="请输入账号昵称" />
        </el-form-item>
        <el-form-item label="账号密码" prop="accountPassword">
          <el-input v-model="form.accountPassword" placeholder="请输入账号密码" type="password" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listAccount, getAccount, delAccount, addAccount, updateAccount } from "@/api/events/account";

export default {
  name: "Account",
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
      // 账号管理表格数据
      accountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleId: null,
        accountSchool: null,
        accountName: null,
        accountGroup: null,
        accountNick: null,
        accountPassword: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询账号管理列表 */
    getList() {
      this.loading = true;
      listAccount(this.queryParams).then(response => {
        this.accountList = response.rows;
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
        accountId: null,
        roleId: null,
        accountSchool: null,
        accountName: null,
        accountGroup: null,
        accountNick: null,
        accountPassword: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.accountId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账号管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const accountId = row.accountId || this.ids
      getAccount(accountId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账号管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.accountId != null) {
            updateAccount(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAccount(this.form).then(response => {
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
      const accountIds = row.accountId || this.ids;
      this.$modal.confirm('是否确认删除账号管理编号为"' + accountIds + '"的数据项？').then(function() {
        return delAccount(accountIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('events/account/export', {
        ...this.queryParams
      }, `account_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
