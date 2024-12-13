package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CollectInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.ICollectInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/collect-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollectInfoController {

    private final ICollectInfoService collectInfoService;

    private final IUserInfoService userInfoService;

    /**
     * 分页获取商品收藏信息
     *
     * @param page        分页对象
     * @param collectInfo 商品收藏信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CollectInfo> page, CollectInfo collectInfo) {
        return R.ok(collectInfoService.selectCollectPage(page, collectInfo));
    }

    /**
     * 根据用户ID获取收藏商品信息
     *
     * @param userId     用户ID
     * @param merchantId 商家ID
     * @return 结果
     */
    @GetMapping("/selectCollectByUser")
    public R selectCollectByUser(Integer userId, Integer merchantId) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
        if (null == userInfo) {
            return R.ok(Collections.emptyList());
        }
        return R.ok(collectInfoService.list(Wrappers.<CollectInfo>lambdaQuery().eq(CollectInfo::getUserId, userInfo.getId()).eq(CollectInfo::getMerchantId, merchantId)));
    }

    /**
     * 根据用户ID获取收藏商品信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/selectCollectByUserId/{userId}")
    public R selectCollectByUserId(@PathVariable("userId") Integer userId) {
        return R.ok(collectInfoService.selectCollectByUserId(userId));
    }

    /**
     * 获取ID获取商品收藏详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(collectInfoService.getById(id));
    }

    /**
     * 获取商品收藏信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(collectInfoService.list());
    }

    /**
     * 新增商品收藏信息
     *
     * @param collectInfo 商品收藏信息
     * @return 结果
     */
    @PostMapping
    public R save(CollectInfo collectInfo) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, collectInfo.getUserId()));
        if (userInfo != null) {
            collectInfo.setUserId(userInfo.getId());
        }
        collectInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(collectInfoService.save(collectInfo));
    }

    /**
     * 修改商品收藏信息
     *
     * @param collectInfo 商品收藏信息
     * @return 结果
     */
    @PutMapping
    public R edit(CollectInfo collectInfo) {
        return R.ok(collectInfoService.updateById(collectInfo));
    }

    /**
     * 删除商品收藏信息
     *
     * @param ids ids
     * @return 商品收藏信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(collectInfoService.removeByIds(ids));
    }

    /**
     * 删除商品收藏信息
     *
     * @return 商品收藏信息
     */
    @DeleteMapping("/deleteById")
    public R deleteById(Integer userId, Integer furnitureId, Integer merchantId) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
        if (userInfo == null) {
            return R.ok(false);
        }

        return R.ok(collectInfoService.remove(Wrappers.<CollectInfo>lambdaQuery().eq(CollectInfo::getUserId, userInfo.getId()).eq(CollectInfo::getFurnitureId, furnitureId).eq(CollectInfo::getMerchantId, merchantId)));
    }
}
