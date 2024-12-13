package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FirnitureTypeInfo;
import cc.mrbird.febs.cos.service.IFirnitureTypeInfoService;
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
@RequestMapping("/cos/firniture-type-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FirnitureTypeInfoController {

    private final IFirnitureTypeInfoService firnitureTypeInfoService;

    /**
     * 分页获取商品类型信息
     *
     * @param page              分页对象
     * @param firnitureTypeInfo 商品类型信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FirnitureTypeInfo> page, FirnitureTypeInfo firnitureTypeInfo) {
        return R.ok(firnitureTypeInfoService.selectFirnitureTypePage(page, firnitureTypeInfo));
    }

    /**
     * 获取ID获取商品类型详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(firnitureTypeInfoService.getById(id));
    }

    /**
     * 获取商品类型信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(firnitureTypeInfoService.list());
    }

    /**
     * 新增商品类型信息
     *
     * @param firnitureTypeInfo 商品类型信息
     * @return 结果
     */
    @PostMapping
    public R save(FirnitureTypeInfo firnitureTypeInfo) {
        firnitureTypeInfo.setCode("FT-" + System.currentTimeMillis());
        firnitureTypeInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(firnitureTypeInfoService.save(firnitureTypeInfo));
    }

    /**
     * 修改商品类型信息
     *
     * @param firnitureTypeInfo 商品类型信息
     * @return 结果
     */
    @PutMapping
    public R edit(FirnitureTypeInfo firnitureTypeInfo) {
        return R.ok(firnitureTypeInfoService.updateById(firnitureTypeInfo));
    }

    /**
     * 删除商品类型信息
     *
     * @param ids ids
     * @return 商品类型信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(firnitureTypeInfoService.removeByIds(ids));
    }
}
