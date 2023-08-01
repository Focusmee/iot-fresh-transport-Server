package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.DeviceControl;
import com.example.iotfreshtransportserver.mapper.DeviceControlMapper;
import com.example.iotfreshtransportserver.service.DeviceControlService;
import org.springframework.stereotype.Service;

/**
 * (DeviceControl)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:53
 */
@Service("deviceControlService")
public class DeviceControlServiceImpl extends ServiceImpl<DeviceControlMapper, DeviceControl> implements DeviceControlService {
    public DeviceControl getDeviceControlByVID(String vid) {
        QueryWrapper<DeviceControl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }

    public void updateDeviceControl(String vid, double bright, double speedM1, double speedM2) {
        DeviceControl deviceControl = new DeviceControl();
        deviceControl.setBright(bright);
        deviceControl.setSpeedm1(speedM1);
        deviceControl.setSpeedm2(speedM2);
        UpdateWrapper<DeviceControl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(deviceControl, updateWrapper);
    }

    /**
     * 更新正常数据（正常数据只有一个）
     *
     * @param deviceControl 设备control
     */

}

