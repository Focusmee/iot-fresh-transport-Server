package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.TransportCabin;


/**
 * (TransportCabin)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface TransportCabinService extends IService<TransportCabin> {

    TransportCabin getTransportCabinByVID(String vid);

    void updateTransportCabin(String vid, String pid);

    void addTransportCabin(String vid, String pid);

    void deleteTransportCabinByVID(String vid);

}

