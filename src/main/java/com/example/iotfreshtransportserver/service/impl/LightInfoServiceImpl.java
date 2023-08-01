package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.domain.vo.PageVo;
import com.example.iotfreshtransportserver.mapper.LightInfoMapper;
import com.example.iotfreshtransportserver.service.LightInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * (LightInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Service("lightInfoService")
public class LightInfoServiceImpl extends ServiceImpl<LightInfoMapper, LightInfo> implements LightInfoService {
    public List<LightInfo> getLightInfoByVID(String vid) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("cabinId", vid);
        return list(queryWrapper);
    }

    public void updateLightInfo(String vid, double lxin, double lxd, String tbegin, String tend) {
        LightInfo lightInfo = new LightInfo();
        lightInfo.setLxin(lxin);
        lightInfo.setLxd(lxd);
        lightInfo.setTbegin(LocalDateTime.parse(tbegin));
        lightInfo.setTend(LocalDateTime.parse(tend));
        UpdateWrapper<LightInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cabin_id", vid);
        update(lightInfo, updateWrapper);
    }

    @Override
    public List<LightInfo> getNewList(Integer cabinId,Integer size) {
        // Create a QueryWrapper to build the query condition
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();

        // Set the ordering to descending based on the time column (assuming the column name is "time")
        queryWrapper.orderByDesc("time");

        // Add the cabinId to the query condition
        queryWrapper.eq("cabinId", cabinId);

        // Set the limit on the number of records to be retrieved (in this case, 6)
        queryWrapper.last("LIMIT " + size);

        // Perform the query using MyBatis-Plus's list method with the QueryWrapper
        List<LightInfo> resultList = baseMapper.selectList(queryWrapper);

        return resultList;
    }
    // 实现根据起始时间和终止时间找到光照信息
    @Override
    public List<LightInfo> getLightInfoByTime(String vid, LocalDateTime start, LocalDateTime end) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabinId", vid);
        queryWrapper.between("time", start, end);
        return list(queryWrapper);
        //return baseMapper.getLightInfoByTime(vid, start, end);
    }

    @Override
    public ResponseResult<PageVo> selectLightInfoListByTime(String vid, LocalDateTime start, LocalDateTime end, Integer pageNum, Integer pageSize) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabinId", vid);
        queryWrapper.orderByDesc("time");
        queryWrapper.between("time", start, end);
        Page<LightInfo> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        List<LightInfo> records = page.getRecords();
        PageVo pageVo = new PageVo(records,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<PageVo> selectLightInfoListByVID(String vid, Integer pageNum, Integer pageSize) {
        QueryWrapper<LightInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cabinId", vid);
        queryWrapper.orderByDesc("time");
        Page<LightInfo> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        List<LightInfo> records = page.getRecords();
        PageVo pageVo = new PageVo(records,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}

