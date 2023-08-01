package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.entity.DeviceControl;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;
import com.example.iotfreshtransportserver.domain.vo.PageVo;

import java.time.LocalDateTime;


/**
 * (DeviceStatus)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface DeviceStatusService extends IService<DeviceStatus> {

    DeviceStatus getNewDeviceStatusByVID(String vid);

    ResponseResult<PageVo> getAbnormalDeviceStatusListByVID(String vid, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize);

    void updateNormal(DeviceStatus deviceStatus);
}

