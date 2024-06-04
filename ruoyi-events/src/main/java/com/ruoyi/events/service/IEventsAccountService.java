package com.ruoyi.events.service;

import java.util.List;
import com.ruoyi.events.domain.EventsAccount;

/**
 * 账号管理Service接口
 * 
 * @author nzp
 * @date 2024-06-04
 */
public interface IEventsAccountService 
{
    /**
     * 查询账号管理
     * 
     * @param accountId 账号管理主键
     * @return 账号管理
     */
    public EventsAccount selectEventsAccountByAccountId(Long accountId);

    /**
     * 查询账号管理列表
     * 
     * @param eventsAccount 账号管理
     * @return 账号管理集合
     */
    public List<EventsAccount> selectEventsAccountList(EventsAccount eventsAccount);

}
