package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;


/**
 * (LightInfo)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface LightInfoService extends IService<LightInfo> {

    LightInfo getLightInfoByVID(String vid);

    void updateLightInfo(String vid, double lxin, double lxd, String tbegin, String tend);
}

