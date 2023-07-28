package com.example.iotfreshtransportserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.iotfreshtransportserver.domain.command.PublishCommand;
import org.apache.ibatis.annotations.Mapper;


/**
 * (PublishCommand)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 09:47:05
 */
@Mapper
public interface PublishCommandMapper extends BaseMapper<PublishCommand> {

}
    
