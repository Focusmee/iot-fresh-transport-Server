package com.example.iotfreshtransportserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iotfreshtransportserver.domain.entity.command.RemoteControlCommand;
import org.apache.ibatis.annotations.Mapper;


/**
 * (RemoteControlCommand)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-24 19:39:16
 */
@Mapper
public interface RemoteControlCommandMapper extends BaseMapper<RemoteControlCommand> {

}
    
