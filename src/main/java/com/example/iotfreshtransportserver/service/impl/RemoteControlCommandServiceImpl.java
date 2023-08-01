package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.command.RemoteControlCommand;
import com.example.iotfreshtransportserver.mapper.RemoteControlCommandMapper;
import com.example.iotfreshtransportserver.service.RemoteControlCommandService;
import org.springframework.stereotype.Service;

/**
 * (RemoteControlCommand)表服务实现类
 *
 * @author makejava
 * @since 2023-07-24 19:39:16
 */
@Service("remoteControlCommandService")
public class RemoteControlCommandServiceImpl extends ServiceImpl<RemoteControlCommandMapper, RemoteControlCommand> implements RemoteControlCommandService {

}

