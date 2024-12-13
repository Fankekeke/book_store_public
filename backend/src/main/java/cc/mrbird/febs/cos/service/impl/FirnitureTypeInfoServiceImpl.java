package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.FirnitureTypeInfo;
import cc.mrbird.febs.cos.dao.FirnitureTypeInfoMapper;
import cc.mrbird.febs.cos.service.IFirnitureTypeInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class FirnitureTypeInfoServiceImpl extends ServiceImpl<FirnitureTypeInfoMapper, FirnitureTypeInfo> implements IFirnitureTypeInfoService {

    /**
     * 分页获取商品类型信息
     *
     * @param page              分页对象
     * @param firnitureTypeInfo 商品类型信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectFirnitureTypePage(Page<FirnitureTypeInfo> page, FirnitureTypeInfo firnitureTypeInfo) {
        return baseMapper.selectFirnitureTypePage(page, firnitureTypeInfo);
    }
}
