package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CommunityInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ICommunityInfoService extends IService<CommunityInfo> {

    /**
     * 分页获取社区信息
     *
     * @param page          分页对象
     * @param communityInfo 社区信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryCommunityPage(Page<CommunityInfo> page, CommunityInfo communityInfo);
}
