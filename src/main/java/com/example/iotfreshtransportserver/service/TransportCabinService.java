package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.TransportCabin;

import java.util.List;


/**
 * (TransportCabin)表服务接口
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
public interface TransportCabinService extends IService<TransportCabin> {

    List<TransportCabin> getTransportCabinByVID(Integer vid);

    void updateTransportCabin(Integer vid, Integer pid);

    void addTransportCabin(Integer vid, Integer pid);

    void deleteTransportCabinByVID(Integer vid);

}

