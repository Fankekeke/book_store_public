package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CollectInfo;
import cc.mrbird.febs.cos.dao.CollectInfoMapper;
import cc.mrbird.febs.cos.entity.DishesInfo;
import cc.mrbird.febs.cos.entity.MerchantInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.ICollectInfoService;
import cc.mrbird.febs.cos.service.IDishesInfoService;
import cc.mrbird.febs.cos.service.IMerchantInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollectInfoServiceImpl extends ServiceImpl<CollectInfoMapper, CollectInfo> implements ICollectInfoService {

    private final IUserInfoService userInfoService;

    private final IDishesInfoService dishInfoService;

    private final IMerchantInfoService merchantInfoService;

    /**
     * 分页获取商品收藏信息
     *
     * @param page        分页对象
     * @param collectInfo 商品收藏信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, CollectInfo collectInfo) {
        return baseMapper.selectCollectPage(page, collectInfo);
    }

    /**
     * 根据用户ID获取收藏商品信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> selectCollectByUserId(Integer userId) {
        // 获取用户信息
        UserInfo userInfo = userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
        if (null == userInfo) {
            return Collections.emptyList();
        }

        // 获取用户收藏商品信息
        List<CollectInfo> collectInfoList = this.list(Wrappers.<CollectInfo>lambdaQuery().eq(CollectInfo::getUserId, userInfo.getId()));
        List<Integer> furnitureIds = collectInfoList.stream().map(CollectInfo::getFurnitureId).distinct().collect(Collectors.toList());
        List<Integer> merchantIds = collectInfoList.stream().map(CollectInfo::getMerchantId).distinct().collect(Collectors.toList());

        List<DishesInfo> furnitureList = dishInfoService.list(Wrappers.<DishesInfo>lambdaQuery().in(DishesInfo::getId, furnitureIds));
        Map<Integer, DishesInfo> furnitureMap = furnitureList.stream().collect(Collectors.toMap(DishesInfo::getId, e -> e));

        List<MerchantInfo> merchantList = merchantInfoService.list(Wrappers.<MerchantInfo>lambdaQuery().in(MerchantInfo::getId, merchantIds));
        Map<Integer, MerchantInfo> merchantMap = merchantList.stream().collect(Collectors.toMap(MerchantInfo::getId, e -> e));

        // 返回数据
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        for (CollectInfo collect : collectInfoList) {
            LinkedHashMap<String, Object> resultItem = new LinkedHashMap<String, Object>() {
                {
                    put("furniture", furnitureMap.get(collect.getFurnitureId()));
                    put("merchant", merchantMap.get(collect.getMerchantId()));
                }
            };
            result.add(resultItem);
        }
        return result;
    }
}
