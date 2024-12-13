package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CommunityInfo;
import cc.mrbird.febs.cos.dao.CommunityInfoMapper;
import cc.mrbird.febs.cos.service.ICommunityInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class CommunityInfoServiceImpl extends ServiceImpl<CommunityInfoMapper, CommunityInfo> implements ICommunityInfoService {

    /**
     * 分页获取社区信息
     *
     * @param page          分页对象
     * @param communityInfo 社区信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryCommunityPage(Page<CommunityInfo> page, CommunityInfo communityInfo) {
        return baseMapper.queryCommunityPage(page, communityInfo);
    }
}
