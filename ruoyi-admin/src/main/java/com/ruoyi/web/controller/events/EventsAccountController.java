package com.ruoyi.web.controller.events;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.events.domain.EventsAccount;
import com.ruoyi.events.service.IEventsAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账号管理Controller
 * 
 * @author nzp
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/events/account")
public class EventsAccountController extends BaseController
{
    @Autowired
    private IEventsAccountService eventsAccountService;

    /**
     * 查询账号管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventsAccount eventsAccount)
    {
        startPage();
        List<EventsAccount> list = eventsAccountService.selectEventsAccountList(eventsAccount);
        return getDataTable(list);
    }

    /**
     * 导出账号管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:account:export')")
    @Log(title = "账号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EventsAccount eventsAccount)
    {
        List<EventsAccount> list = eventsAccountService.selectEventsAccountList(eventsAccount);

        ExcelUtil<EventsAccount> util = new ExcelUtil<EventsAccount>(EventsAccount.class);
        util.exportExcel(response, list, "账号管理数据");
    }

    /**
     * 获取账号管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('events:account:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId)
    {
        return success(eventsAccountService.selectEventsAccountByAccountId(accountId));
    }

}
