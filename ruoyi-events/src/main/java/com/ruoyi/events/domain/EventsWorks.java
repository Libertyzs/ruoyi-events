package com.ruoyi.events.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作品管理对象 events_works
 * 
 * @author nzp
 * @date 2024-06-04
 */
public class EventsWorks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作品ID */
    private Long workId;

    /** 参赛账号ID */
    @Excel(name = "参赛账号ID")
    private Long accountId;

    /** 学校 */
    @Excel(name = "学校")
    private String workSchool;

    /** 报名组别 */
    @Excel(name = "报名组别")
    private String workGroup;

    /** 作品名称 */
    @Excel(name = "作品名称")
    private String workName;

    /** 参赛姓名 */
    @Excel(name = "参赛姓名")
    private String workAuthor;

    /** 作品详情 */
    private String workDetails;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    /** 实施平均分 */
    @Excel(name = "实施平均分")
    private BigDecimal averageScore;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submissionTime;

    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setAccountId(Long accountId) 
    {
        this.accountId = accountId;
    }

    public Long getAccountId() 
    {
        return accountId;
    }
    public void setWorkSchool(String workSchool) 
    {
        this.workSchool = workSchool;
    }

    public String getWorkSchool() 
    {
        return workSchool;
    }
    public void setWorkGroup(String workGroup) 
    {
        this.workGroup = workGroup;
    }

    public String getWorkGroup() 
    {
        return workGroup;
    }
    public void setWorkName(String workName) 
    {
        this.workName = workName;
    }

    public String getWorkName() 
    {
        return workName;
    }
    public void setWorkAuthor(String workAuthor) 
    {
        this.workAuthor = workAuthor;
    }

    public String getWorkAuthor() 
    {
        return workAuthor;
    }
    public void setWorkDetails(String workDetails) 
    {
        this.workDetails = workDetails;
    }

    public String getWorkDetails() 
    {
        return workDetails;
    }
    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
    }
    public void setAverageScore(BigDecimal averageScore) 
    {
        this.averageScore = averageScore;
    }

    public BigDecimal getAverageScore() 
    {
        return averageScore;
    }
    public void setSubmissionTime(Date submissionTime) 
    {
        this.submissionTime = submissionTime;
    }

    public Date getSubmissionTime() 
    {
        return submissionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workId", getWorkId())
            .append("accountId", getAccountId())
            .append("workSchool", getWorkSchool())
            .append("workGroup", getWorkGroup())
            .append("workName", getWorkName())
            .append("workAuthor", getWorkAuthor())
            .append("workDetails", getWorkDetails())
            .append("contactInfo", getContactInfo())
            .append("averageScore", getAverageScore())
            .append("submissionTime", getSubmissionTime())
            .toString();
    }
}
