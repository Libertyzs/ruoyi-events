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
import com.ruoyi.events.domain.EventsEvaluate;
import com.ruoyi.events.service.IEventsEvaluateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评价管理Controller
 * 
 * @author nzp
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/events/evaluate")
public class EventsEvaluateController extends BaseController
{
    @Autowired
    private IEventsEvaluateService eventsEvaluateService;

    /**
     * 查询评价管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventsEvaluate eventsEvaluate)
    {
        startPage();
        List<EventsEvaluate> list = eventsEvaluateService.selectEventsEvaluateList(eventsEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出评价管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:export')")
    @Log(title = "评价管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EventsEvaluate eventsEvaluate)
    {
        List<EventsEvaluate> list = eventsEvaluateService.selectEventsEvaluateList(eventsEvaluate);
        ExcelUtil<EventsEvaluate> util = new ExcelUtil<EventsEvaluate>(EventsEvaluate.class);
        util.exportExcel(response, list, "评价管理数据");
    }

    /**
     * 获取评价管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:query')")
    @GetMapping(value = "/{evaluateId}")
    public AjaxResult getInfo(@PathVariable("evaluateId") Long evaluateId)
    {
        return success(eventsEvaluateService.selectEventsEvaluateByEvaluateId(evaluateId));
    }

    /**
     * 新增评价管理
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:add')")
    @Log(title = "评价管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventsEvaluate eventsEvaluate)
    {
        return toAjax(eventsEvaluateService.insertEventsEvaluate(eventsEvaluate));
    }

    /**
     * 修改评价管理
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:edit')")
    @Log(title = "评价管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventsEvaluate eventsEvaluate)
    {
        return toAjax(eventsEvaluateService.updateEventsEvaluate(eventsEvaluate));
    }

    /**
     * 删除评价管理
     */
    @PreAuthorize("@ss.hasPermi('events:evaluate:remove')")
    @Log(title = "评价管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{evaluateIds}")
    public AjaxResult remove(@PathVariable Long[] evaluateIds)
    {
        return toAjax(eventsEvaluateService.deleteEventsEvaluateByEvaluateIds(evaluateIds));
    }
}
