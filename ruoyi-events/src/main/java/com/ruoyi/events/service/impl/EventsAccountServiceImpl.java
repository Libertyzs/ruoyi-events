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

    /**
     * 新增账号管理
     * 
     * @param eventsAccount 账号管理
     * @return 结果
     */
    @Override
    public int insertEventsAccount(EventsAccount eventsAccount)
    {
        eventsAccount.setCreateTime(DateUtils.getNowDate());
        switch (eventsAccount.getRoleName()) {
            case "教师":
                eventsAccount.setRoleId(1l);
                break;
            case "评委":
                eventsAccount.setRoleId(2l);
                break;
            default:
                return 0;
        }

        // 对添加账号做一下校验
        // 1. 可以对账号唯一性做限制
        // 2. 密码长度校验 密码密文加密  BCryptPassword加密即可，再查询的时候进行解密  直接注入使用就可以
        // 3. 可以对账号唯一性做限制

        return eventsAccountMapper.insertEventsAccount(eventsAccount);
    }

    /**
     * 修改账号管理
     * 
     * @param eventsAccount 账号管理
     * @return 结果
     */
    @Override
    public int updateEventsAccount(EventsAccount eventsAccount)
    {
        eventsAccount.setUpdateTime(DateUtils.getNowDate());
        if(eventsAccount.getRoleName().equals("教师")){
            eventsAccount.setRoleId(1l);
        }else if (eventsAccount.getRoleName().equals("评委")){
            eventsAccount.setRoleId(2l);
        }else{
            return 0;
        }
        return eventsAccountMapper.updateEventsAccount(eventsAccount);
    }

    /**
     * 批量删除账号管理
     * 
     * @param accountIds 需要删除的账号管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsAccountByAccountIds(Long[] accountIds)
    {
        return eventsAccountMapper.deleteEventsAccountByAccountIds(accountIds);
    }

    /**
     * 删除账号管理信息
     * 
     * @param accountId 账号管理主键
     * @return 结果
     */
    @Override
    public int deleteEventsAccountByAccountId(Long accountId)
    {
        return eventsAccountMapper.deleteEventsAccountByAccountId(accountId);
    }
}
