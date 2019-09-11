package com.tlj.dreamdo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlj.dreamdo.system.enums.SysUrlConstans;
import com.tlj.dreamdo.system.mapper.SysLogMapper;
import com.tlj.dreamdo.system.model.SysLogModel;
import com.tlj.dreamdo.system.service.SysLogService;
import org.springframework.stereotype.Service;


import java.util.Arrays;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TianXin
 * @since 2019-04-14
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogModel> implements SysLogService {
    
    @Override
    public IPage<SysLogModel> findPage(IPage<SysLogModel> page, Wrapper<SysLogModel> wapper) {
        return baseMapper.findPage(page, wapper);
    }

    @Override
    public int findHomeFlux() {
        LambdaQueryWrapper<SysLogModel> queryWrapper = new LambdaQueryWrapper<SysLogModel>();
        queryWrapper.in(SysLogModel::getUrl, Arrays.asList(SysUrlConstans.INDEX_1,SysUrlConstans.INDEX_2,SysUrlConstans.INDEX_3));
        return baseMapper.selectCount(queryWrapper );
    }

}
