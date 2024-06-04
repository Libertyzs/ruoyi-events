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
import com.ruoyi.events.domain.EventsWorks;
import com.ruoyi.events.service.IEventsWorksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作品管理Controller
 * 
 * @author nzp
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/events/works")
public class EventsWorksController extends BaseController
{
    @Autowired
    private IEventsWorksService eventsWorksService;

    /**
     * 查询作品管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:works:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventsWorks eventsWorks)
    {
        startPage();
        List<EventsWorks> list = eventsWorksService.selectEventsWorksList(eventsWorks);
        return getDataTable(list);
    }

    /**
     * 导出作品管理列表
     */
    @PreAuthorize("@ss.hasPermi('events:works:export')")
    @Log(title = "作品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EventsWorks eventsWorks)
    {
        List<EventsWorks> list = eventsWorksService.selectEventsWorksList(eventsWorks);
        ExcelUtil<EventsWorks> util = new ExcelUtil<EventsWorks>(EventsWorks.class);
        util.exportExcel(response, list, "作品管理数据");
    }

    /**
     * 获取作品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('events:works:query')")
    @GetMapping(value = "/{workId}")
    public AjaxResult getInfo(@PathVariable("workId") Long workId)
    {
        return success(eventsWorksService.selectEventsWorksByWorkId(workId));
    }

    /**
     * 新增作品管理
     */
    @PreAuthorize("@ss.hasPermi('events:works:add')")
    @Log(title = "作品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventsWorks eventsWorks)
    {
        return toAjax(eventsWorksService.insertEventsWorks(eventsWorks));
    }

    /**
     * 修改作品管理
     */
    @PreAuthorize("@ss.hasPermi('events:works:edit')")
    @Log(title = "作品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventsWorks eventsWorks)
    {
        return toAjax(eventsWorksService.updateEventsWorks(eventsWorks));
    }

    /**
     * 删除作品管理
     */
    @PreAuthorize("@ss.hasPermi('events:works:remove')")
    @Log(title = "作品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workIds}")
    public AjaxResult remove(@PathVariable Long[] workIds)
    {
        return toAjax(eventsWorksService.deleteEventsWorksByWorkIds(workIds));
    }
}
