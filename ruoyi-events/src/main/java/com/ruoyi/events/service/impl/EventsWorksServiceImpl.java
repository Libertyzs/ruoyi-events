package com.ruoyi.events.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.events.mapper.EventsWorksMapper;
import com.ruoyi.events.domain.EventsWorks;
import com.ruoyi.events.service.IEventsWorksService;

/**
 * 作品管理Service业务层处理
 * 
 * @author nzp
 * @date 2024-06-04
 */
@Service
public class EventsWorksServiceImpl implements IEventsWorksService 
{
    @Autowired
    private EventsWorksMapper eventsWorksMapper;

    /**
     * 查询作品管理
     * 
     * @param workId 作品管理主键
     * @return 作品管理
     */
    @Override
    public EventsWorks selectEventsWorksByWorkId(Long workId)
    {
        return eventsWorksMapper.selectEventsWorksByWorkId(workId);
    }

    /**
     * 查询作品管理列表
     * 
     * @param eventsWorks 作品管理
     * @return 作品管理
     */
    @Override
    public List<EventsWorks> selectEventsWorksList(EventsWorks eventsWorks)
    {
        return eventsWorksMapper.selectEventsWorksList(eventsWorks);
    }

    /**
     * 新增作品管理
     * 
     * @param eventsWorks 作品管理
     * @return 结果
     */
    @Override
    public int insertEventsWorks(EventsWorks eventsWorks)
    {
        return eventsWorksMapper.insertEventsWorks(eventsWorks);
    }

    /**
     * 修改作品管理
     * 
     * @param eventsWorks 作品管理
     * @return 结果
     */
    @Override
    public int updateEventsWorks(EventsWorks eventsWorks)
    {
        return eventsWorksMapper.updateEventsWorks(eventsWorks);
    }

    /**
     * 批量删除作品管理
     * 
     * @param workIds 需要删除的作品管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsWorksByWorkIds(Long[] workIds)
    {
        return eventsWorksMapper.deleteEventsWorksByWorkIds(workIds);
    }

    /**
     * 删除作品管理信息
     * 
     * @param workId 作品管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsWorksByWorkId(Long workId)
    {
        return eventsWorksMapper.deleteEventsWorksByWorkId(workId);
    }
}
