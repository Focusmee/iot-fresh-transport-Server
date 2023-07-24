package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;


/**
 * (DeviceStatus)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface DeviceStatusService extends IService<DeviceStatus> {

    DeviceStatus getDeviceStatusByVID(String vid);
}

