package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.TransportCabin;
import com.example.iotfreshtransportserver.mapper.TransportCabinMapper;
import com.example.iotfreshtransportserver.service.TransportCabinService;
import org.springframework.stereotype.Service;

/**
 * (TransportCabin)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("transportCabinService")
public class TransportCabinServiceImpl extends ServiceImpl<TransportCabinMapper, TransportCabin> implements TransportCabinService {

    @Override
    public TransportCabin getTransportCabinByVID(String vid) {
        QueryWrapper<TransportCabin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("VID", vid);
        return getOne(queryWrapper);
    }
    public void addTransportCabin(String vid, String pid) {
        TransportCabin transportCabin = new TransportCabin();
        transportCabin.setVid(vid);
        transportCabin.setPid(pid);
        save(transportCabin);
    }

    public void updateTransportCabin(String vid, String pid) {
        TransportCabin transportCabin = new TransportCabin();
        transportCabin.setVid(vid);
        transportCabin.setPid(pid);
        UpdateWrapper<TransportCabin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("VID", vid);
        update(transportCabin, updateWrapper);
    }

    public void deleteTransportCabinByVID(String vid) {
        QueryWrapper<TransportCabin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("VID", vid);
        remove(queryWrapper);
    }
}

