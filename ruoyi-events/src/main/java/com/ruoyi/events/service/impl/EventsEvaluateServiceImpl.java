package com.ruoyi.events.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.events.mapper.EventsEvaluateMapper;
import com.ruoyi.events.domain.EventsEvaluate;
import com.ruoyi.events.service.IEventsEvaluateService;

/**
 * 评价管理Service业务层处理
 * 
 * @author nzp
 * @date 2024-06-04
 */
@Service
public class EventsEvaluateServiceImpl implements IEventsEvaluateService 
{
    @Autowired
    private EventsEvaluateMapper eventsEvaluateMapper;

    /**
     * 查询评价管理
     * 
     * @param evaluateId 评价管理主键
     * @return 评价管理
     */
    @Override
    public EventsEvaluate selectEventsEvaluateByEvaluateId(Long evaluateId)
    {
        return eventsEvaluateMapper.selectEventsEvaluateByEvaluateId(evaluateId);
    }

    /**
     * 查询评价管理列表
     * 
     * @param eventsEvaluate 评价管理
     * @return 评价管理
     */
    @Override
    public List<EventsEvaluate> selectEventsEvaluateList(EventsEvaluate eventsEvaluate)
    {
        return eventsEvaluateMapper.selectEventsEvaluateList(eventsEvaluate);
    }

    /**
     * 新增评价管理
     * 
     * @param eventsEvaluate 评价管理
     * @return 结果
     */
    @Override
    public int insertEventsEvaluate(EventsEvaluate eventsEvaluate)
    {
        return eventsEvaluateMapper.insertEventsEvaluate(eventsEvaluate);
    }

    /**
     * 修改评价管理
     * 
     * @param eventsEvaluate 评价管理
     * @return 结果
     */
    @Override
    public int updateEventsEvaluate(EventsEvaluate eventsEvaluate)
    {
        return eventsEvaluateMapper.updateEventsEvaluate(eventsEvaluate);
    }

    /**
     * 批量删除评价管理
     * 
     * @param evaluateIds 需要删除的评价管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsEvaluateByEvaluateIds(Long[] evaluateIds)
    {
        return eventsEvaluateMapper.deleteEventsEvaluateByEvaluateIds(evaluateIds);
    }

    /**
     * 删除评价管理信息
     * 
     * @param evaluateId 评价管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsEvaluateByEvaluateId(Long evaluateId)
    {
        return eventsEvaluateMapper.deleteEventsEvaluateByEvaluateId(evaluateId);
    }
}
