package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.mapper.TemperatureInfoMapper;
import com.example.iotfreshtransportserver.service.TemperatureInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TemperatureInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("temperatureInfoService")
public class TemperatureInfoServiceImpl extends ServiceImpl<TemperatureInfoMapper, TemperatureInfo> implements TemperatureInfoService {
    public TemperatureInfo getTemperatureInfoByVID(String vid) {
        QueryWrapper<TemperatureInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabin_id", vid);
        return getOne(queryWrapper);
    }

    /**
     * 获得最新列表
     *
     * @param size 大小
     * @return {@link List}<{@link TemperatureInfo}>
     */
    @Override
    public List<TemperatureInfo> getNewList(Integer size) {
        // Create a QueryWrapper to build the query condition
        QueryWrapper<TemperatureInfo> queryWrapper = new QueryWrapper<>();

        // Set the ordering to descending based on the time column (assuming the column name is "time")
        queryWrapper.orderByDesc("time");

        // Set the limit on the number of records to be retrieved (in this case, 6)
        queryWrapper.last("LIMIT " + size);

        // Perform the query using MyBatis-Plus's list method with the QueryWrapper
        List<TemperatureInfo> resultList = baseMapper.selectList(queryWrapper);

        return resultList;
    }

    public void updateTemperatureInfo(String vid, double tin, double tout, double tinDH, double tinDL, double tg) {
        TemperatureInfo temperatureInfo = new TemperatureInfo();
        temperatureInfo.setTin(tin);
        temperatureInfo.setTout(tout);
        temperatureInfo.setTindh(tinDH);
        temperatureInfo.setTindl(tinDL);
        temperatureInfo.setTg(tg);
        UpdateWrapper<TemperatureInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(temperatureInfo, updateWrapper);
    }
}

