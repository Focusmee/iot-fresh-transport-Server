package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.command.PublishCommand;
import com.example.iotfreshtransportserver.mapper.PublishCommandMapper;
import com.example.iotfreshtransportserver.service.PublishCommandService;
import org.springframework.stereotype.Service;

/**
 * (PublishCommand)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 09:47:03
 */
@Service("publishCommandService")
public class PublishCommandServiceImpl extends ServiceImpl<PublishCommandMapper, PublishCommand> implements PublishCommandService {

    @Override
    public PublishCommand getNewByVid(Integer vid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //根据时间排序找到最新的一条数据
        queryWrapper.orderByDesc("time");
        queryWrapper.eq("cabinId", vid);
        return getOne(queryWrapper);
    }
}

