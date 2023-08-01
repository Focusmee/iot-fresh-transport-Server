package com.example.iotfreshtransportserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iotfreshtransportserver.domain.entity.VstatusDescription;
import com.example.iotfreshtransportserver.mapper.VstatusDescriptionMapper;
import com.example.iotfreshtransportserver.service.VstatusDescriptionService;
import org.springframework.stereotype.Service;

/**
 * (VstatusDescription)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 09:55:14
 */
@Service("vstatusDescriptionService")
public class VstatusDescriptionServiceImpl extends ServiceImpl<VstatusDescriptionMapper, VstatusDescription> implements VstatusDescriptionService {

}

