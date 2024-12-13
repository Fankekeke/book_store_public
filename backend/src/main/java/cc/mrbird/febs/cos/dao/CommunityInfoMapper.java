package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CommunityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface CommunityInfoMapper extends BaseMapper<CommunityInfo> {

    /**
     * 分页获取社区信息
     *
     * @param page          分页对象
     * @param communityInfo 社区信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryCommunityPage(Page<CommunityInfo> page, @Param("communityInfo") CommunityInfo communityInfo);
}
