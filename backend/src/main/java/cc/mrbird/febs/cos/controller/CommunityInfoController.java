package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CommunityInfo;
import cc.mrbird.febs.cos.service.ICommunityInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/community-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommunityInfoController {

    private final ICommunityInfoService communityInfoService;

    /**
     * 分页获取社区信息
     *
     * @param page          分页对象
     * @param communityInfo 社区信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CommunityInfo> page, CommunityInfo communityInfo) {
        return R.ok(communityInfoService.queryCommunityPage(page, communityInfo));
    }

    /**
     * 获取ID获取社区详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(communityInfoService.getById(id));
    }

    /**
     * 获取社区信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(communityInfoService.list());
    }

    /**
     * 新增社区信息
     *
     * @param communityInfo 社区信息
     * @return 结果
     */
    @PostMapping
    public R save(CommunityInfo communityInfo) {
        communityInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        communityInfo.setCode("CU-" + System.currentTimeMillis());
        return R.ok(communityInfoService.save(communityInfo));
    }

    /**
     * 修改社区信息
     *
     * @param communityInfo 社区信息
     * @return 结果
     */
    @PutMapping
    public R edit(CommunityInfo communityInfo) {
        return R.ok(communityInfoService.updateById(communityInfo));
    }

    /**
     * 删除社区信息
     *
     * @param ids ids
     * @return 社区信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(communityInfoService.removeByIds(ids));
    }
}
