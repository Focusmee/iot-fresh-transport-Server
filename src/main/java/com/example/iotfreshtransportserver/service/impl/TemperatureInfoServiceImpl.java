package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.mapper.TemperatureInfoMapper;
import com.example.iotfreshtransportserver.service.TemperatureInfoService;
import org.springframework.stereotype.Service;

/**
 * (TemperatureInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("temperatureInfoService")
public class TemperatureInfoServiceImpl extends ServiceImpl<TemperatureInfoMapper, TemperatureInfo> implements TemperatureInfoService {
    public TemperatureInfo getTemperatureInfoByVID(String vid) {
        QueryWrapper<TemperatureInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }

    public void updateTemperatureInfo(String vid, double tin, double tout, double tinDH, double tinDL, double tg) {
        TemperatureInfo temperatureInfo = new TemperatureInfo();
        temperatureInfo.setTin(tin);
        temperatureInfo.setTout(tout);
        temperatureInfo.setTindh(tinDH);
        temperatureInfo.setTindl(tinDL);
        temperatureInfo.setTg(tg);
        UpdateWrapper<TemperatureInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(temperatureInfo, updateWrapper);
    }
}

