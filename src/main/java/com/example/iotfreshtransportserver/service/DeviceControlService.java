package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.DeviceControl;


/**
 * (DeviceControl)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:52
 */
public interface DeviceControlService extends IService<DeviceControl> {

    DeviceControl getDeviceControlByVID(String vid);

    void updateDeviceControl(String vid, double bright, double speedM1, double speedM2);


}

