package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.mapper.LightInfoMapper;
import com.example.iotfreshtransportserver.service.LightInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * (LightInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("lightInfoService")
public class LightInfoServiceImpl extends ServiceImpl<LightInfoMapper, LightInfo> implements LightInfoService {
    public LightInfo getLightInfoByVID(String vid) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }

    public void updateLightInfo(String vid, double lxin, double lxd, String tbegin, String tend) {
        LightInfo lightInfo = new LightInfo();
        lightInfo.setLxin(lxin);
        lightInfo.setLxd(lxd);
        lightInfo.setTbegin(LocalTime.parse(tbegin));
        lightInfo.setTend(LocalTime.parse(tend));
        UpdateWrapper<LightInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(lightInfo, updateWrapper);
    }

    @Override
    public List<LightInfo> getNewList(Integer size) {
        // Create a QueryWrapper to build the query condition
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();

        // Set the ordering to descending based on the time column (assuming the column name is "time")
        queryWrapper.orderByDesc("time");

        // Set the limit on the number of records to be retrieved (in this case, 6)
        queryWrapper.last("LIMIT " + size);

        // Perform the query using MyBatis-Plus's list method with the QueryWrapper
        List<LightInfo> resultList = baseMapper.selectList(queryWrapper);

        return resultList;
    }
}

