package com.example.iotfreshtransportserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * (TemperatureInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@Mapper
public interface TemperatureInfoMapper extends BaseMapper<TemperatureInfo> {

}
    
