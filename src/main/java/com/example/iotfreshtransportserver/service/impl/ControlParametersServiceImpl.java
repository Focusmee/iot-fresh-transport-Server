package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.command.ControlParameter;

import com.example.iotfreshtransportserver.mapper.ControlParametersMapper;
import com.example.iotfreshtransportserver.service.ControlParametersService;
import org.springframework.stereotype.Service;

/**
 * (ControlParameters)表服务实现类
 *
 * @author makejava
 * @since 2023-07-24 19:39:17
 */
@Service("controlParametersService")
public class ControlParametersServiceImpl extends ServiceImpl<ControlParametersMapper, ControlParameter> implements ControlParametersService {

}

