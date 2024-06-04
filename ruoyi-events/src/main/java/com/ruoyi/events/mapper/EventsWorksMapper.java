package com.ruoyi.events.mapper;

import java.util.List;
import com.ruoyi.events.domain.EventsWorks;

/**
 * 作品管理Mapper接口
 * 
 * @author nzp
 * @date 2024-06-04
 */
public interface EventsWorksMapper 
{
    /**
     * 查询作品管理
     * 
     * @param workId 作品管理主键
     * @return 作品管理
     */
    public EventsWorks selectEventsWorksByWorkId(Long workId);

    /**
     * 查询作品管理列表
     * 
     * @param eventsWorks 作品管理
     * @return 作品管理集合
     */
    public List<EventsWorks> selectEventsWorksList(EventsWorks eventsWorks);

    /**
     * 新增作品管理
     * 
     * @param eventsWorks 作品管理
     * @return 结果
     */
    public int insertEventsWorks(EventsWorks eventsWorks);

    /**
     * 修改作品管理
     * 
     * @param eventsWorks 作品管理
     * @return 结果
     */
    public int updateEventsWorks(EventsWorks eventsWorks);

    /**
     * 删除作品管理
     * 
     * @param workId 作品管理主键
     * @return 结果
     */
    public int deleteEventsWorksByWorkId(Long workId);

    /**
     * 批量删除作品管理
     * 
     * @param workIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventsWorksByWorkIds(Long[] workIds);
}
