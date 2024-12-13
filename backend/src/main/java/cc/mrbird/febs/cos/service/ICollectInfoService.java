package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CollectInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface ICollectInfoService extends IService<CollectInfo> {

    /**
     * 分页获取商品收藏信息
     *
     * @param page        分页对象
     * @param collectInfo 商品收藏信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, CollectInfo collectInfo);

    /**
     * 根据用户ID获取收藏商品信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectCollectByUserId(Integer userId);
}
