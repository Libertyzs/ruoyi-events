package com.ruoyi.events.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.events.mapper.EventsAccountMapper;
import com.ruoyi.events.domain.EventsAccount;
import com.ruoyi.events.service.IEventsAccountService;

/**
 * 账号管理Service业务层处理
 * 
 * @author nzp
 * @date 2024-06-04
 */
@Service
public class EventsAccountServiceImpl implements IEventsAccountService 
{
    @Autowired
    private EventsAccountMapper eventsAccountMapper;

    /**
     * 查询账号管理
     * 
     * @param accountId 账号管理主键
     * @return 账号管理
     */
    @Override
    public EventsAccount selectEventsAccountByAccountId(Long accountId)
    {
        return eventsAccountMapper.selectEventsAccountByAccountId(accountId);
    }

    /**
     * 查询账号管理列表
     * 
     * @param eventsAccount 账号管理
     * @return 账号管理
     */
    @Override
    public List<EventsAccount> selectEventsAccountList(EventsAccount eventsAccount)
    {
        return eventsAccountMapper.selectEventsAccountList(eventsAccount);
    }

}
