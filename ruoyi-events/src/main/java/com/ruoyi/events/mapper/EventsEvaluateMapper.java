package com.ruoyi.events.mapper;

import java.util.List;
import com.ruoyi.events.domain.EventsEvaluate;

/**
 * 评价管理Mapper接口
 * 
 * @author nzp
 * @date 2024-06-04
 */
public interface EventsEvaluateMapper 
{
    /**
     * 查询评价管理
     * 
     * @param evaluateId 评价管理主键
     * @return 评价管理
     */
    public EventsEvaluate selectEventsEvaluateByEvaluateId(Long evaluateId);

    /**
     * 查询评价管理列表
     * 
     * @param eventsEvaluate 评价管理
     * @return 评价管理集合
     */
    public List<EventsEvaluate> selectEventsEvaluateList(EventsEvaluate eventsEvaluate);

    /**
     * 新增评价管理
     * 
     * @param eventsEvaluate 评价管理
     * @return 结果
     */
    public int insertEventsEvaluate(EventsEvaluate eventsEvaluate);

    /**
     * 修改评价管理
     * 
     * @param eventsEvaluate 评价管理
     * @return 结果
     */
    public int updateEventsEvaluate(EventsEvaluate eventsEvaluate);

    /**
     * 删除评价管理
     * 
     * @param evaluateId 评价管理主键
     * @return 结果
     */
    public int deleteEventsEvaluateByEvaluateId(Long evaluateId);

    /**
     * 批量删除评价管理
     * 
     * @param evaluateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventsEvaluateByEvaluateIds(Long[] evaluateIds);
}
