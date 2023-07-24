package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.mapper.LightInfoMapper;
import com.example.iotfreshtransportserver.service.LightInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * (LightInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("lightInfoService")
public class LightInfoServiceImpl extends ServiceImpl<LightInfoMapper, LightInfo> implements LightInfoService {
    public LightInfo getLightInfoByVID(String vid) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }

    public void updateLightInfo(String vid, double lxin, double lxd, String tbegin, String tend) {
        LightInfo lightInfo = new LightInfo();
        lightInfo.setLxin(lxin);
        lightInfo.setLxd(lxd);
        lightInfo.setTbegin(LocalTime.parse(tbegin));
        lightInfo.setTend(LocalTime.parse(tend));
        UpdateWrapper<LightInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(lightInfo, updateWrapper);
    }
}

