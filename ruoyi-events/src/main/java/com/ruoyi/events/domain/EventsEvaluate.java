package com.ruoyi.events.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评价管理对象 events_evaluate
 * 
 * @author nzp
 * @date 2024-06-04
 */
public class EventsEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    private Long evaluateId;

    /** 作品ID */
    @Excel(name = "作品ID")
    private Long workId;

    /** 学校 */
    @Excel(name = "学校")
    private String school;

    /** 作品名称 */
    @Excel(name = "作品名称")
    private String workName;

    /** 评委姓名 */
    @Excel(name = "评委姓名")
    private String judgeName;

    /** 得分 */
    @Excel(name = "得分")
    private BigDecimal score;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluationContent;

    public void setEvaluateId(Long evaluateId) 
    {
        this.evaluateId = evaluateId;
    }

    public Long getEvaluateId() 
    {
        return evaluateId;
    }
    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setWorkName(String workName) 
    {
        this.workName = workName;
    }

    public String getWorkName() 
    {
        return workName;
    }
    public void setJudgeName(String judgeName) 
    {
        this.judgeName = judgeName;
    }

    public String getJudgeName() 
    {
        return judgeName;
    }
    public void setScore(BigDecimal score) 
    {
        this.score = score;
    }

    public BigDecimal getScore() 
    {
        return score;
    }
    public void setEvaluationContent(String evaluationContent) 
    {
        this.evaluationContent = evaluationContent;
    }

    public String getEvaluationContent() 
    {
        return evaluationContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("evaluateId", getEvaluateId())
            .append("workId", getWorkId())
            .append("school", getSchool())
            .append("workName", getWorkName())
            .append("judgeName", getJudgeName())
            .append("score", getScore())
            .append("evaluationContent", getEvaluationContent())
            .toString();
    }
}
