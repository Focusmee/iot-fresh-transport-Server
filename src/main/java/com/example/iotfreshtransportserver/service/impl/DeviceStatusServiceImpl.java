package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;
import com.example.iotfreshtransportserver.mapper.DeviceStatusMapper;
import com.example.iotfreshtransportserver.service.DeviceStatusService;
import org.springframework.stereotype.Service;

/**
 * (DeviceStatus)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("deviceStatusService")
public class DeviceStatusServiceImpl extends ServiceImpl<DeviceStatusMapper, DeviceStatus> implements DeviceStatusService {
    public DeviceStatus getDeviceStatusByVID(String vid) {
        QueryWrapper<DeviceStatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }
}

