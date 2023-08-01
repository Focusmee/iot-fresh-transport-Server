package com.example.iotfreshtransportserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iotfreshtransportserver.domain.entity.command.PublishCommand;


/**
 * (PublishCommand)表服务接口
 *
 * @author makejava
 * @since 2023-07-28 09:47:01
 */
public interface PublishCommandService extends IService<PublishCommand> {

    PublishCommand getNewByVid(Integer vid);
}

