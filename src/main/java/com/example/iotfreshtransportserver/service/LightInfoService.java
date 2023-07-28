package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;

import java.time.LocalDateTime;
import java.util.List;


/**
 * (LightInfo)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface LightInfoService extends IService<LightInfo> {

    List<LightInfo> getLightInfoByVID(String vid);

    void updateLightInfo(String vid, double lxin, double lxd, String tbegin, String tend);

    List<LightInfo> getNewList(Integer vid, Integer size);

    // 实现根据起始时间和终止时间找到光照信息
    List<LightInfo> getLightInfoByTime(String vid, LocalDateTime start, LocalDateTime end);
}

