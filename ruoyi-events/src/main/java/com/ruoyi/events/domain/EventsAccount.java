package com.ruoyi.events.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 账号管理对象 events_account
 *
 * @author nzp
 * @date 2024-06-04
 */
public class EventsAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long accountId;

    /** 身份id */
    @Excel(name = "身份id")
    private Long roleId;

    @Excel(name = "身份名称")
    private String roleName;

    /** 学校 */
    @Excel(name = "学校")
    private String accountSchool;

    /** 姓名 */
    @Excel(name = "姓名")
    private String accountName;

    /** 报名组别 */
    @Excel(name = "报名组别")
    private String accountGroup;

    /** 账号昵称 */
    @Excel(name = "账号昵称")
    private String accountNick;

    /** 账号密码 */
    @Excel(name = "账号密码")
    private String accountPassword;

    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountId()
    {
        return accountId;
    }
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }
    public void setAccountSchool(String accountSchool)
    {
        this.accountSchool = accountSchool;
    }

    public String getAccountSchool()
    {
        return accountSchool;
    }
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountName()
    {
        return accountName;
    }
    public void setAccountGroup(String accountGroup)
    {
        this.accountGroup = accountGroup;
    }

    public String getAccountGroup()
    {
        return accountGroup;
    }
    public void setAccountNick(String accountNick)
    {
        this.accountNick = accountNick;
    }

    public String getAccountNick()
    {
        return accountNick;
    }
    public void setAccountPassword(String accountPassword)
    {
        this.accountPassword = accountPassword;
    }

    public String getAccountPassword()
    {
        return accountPassword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("accountId", getAccountId())
            .append("roleId", getRoleId())
            .append("accountSchool", getAccountSchool())
            .append("accountName", getAccountName())
            .append("accountGroup", getAccountGroup())
            .append("accountNick", getAccountNick())
            .append("accountPassword", getAccountPassword())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
