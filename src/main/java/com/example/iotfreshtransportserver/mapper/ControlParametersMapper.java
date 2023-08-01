package com.example.iotfreshtransportserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.iotfreshtransportserver.domain.entity.command.ControlParameter;
import org.apache.ibatis.annotations.Mapper;


/**
 * (ControlParameters)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-24 19:39:17
 */
@Mapper
public interface ControlParametersMapper extends BaseMapper<ControlParameter> {

}
    
