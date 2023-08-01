package com.example.iotfreshtransportserver.handler.biz;

import com.alibaba.fastjson.JSON;
import com.example.iotfreshtransportserver.domain.dto.ReceivedDataDto;
import com.example.iotfreshtransportserver.domain.entity.DeviceStatus;
import com.example.iotfreshtransportserver.domain.entity.LightInfo;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.domain.entity.TransportCabin;
import com.example.iotfreshtransportserver.enums.TopicTypeEnum;
import com.example.iotfreshtransportserver.handler.TopicTypeHandler;
import com.example.iotfreshtransportserver.handler.anno.TopicTypeHandlerAnno;
import com.example.iotfreshtransportserver.service.DeviceStatusService;
import com.example.iotfreshtransportserver.service.LightInfoService;
import com.example.iotfreshtransportserver.service.TemperatureInfoService;
import com.example.iotfreshtransportserver.service.TransportCabinService;
import com.example.iotfreshtransportserver.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@TopicTypeHandlerAnno(TopicTypeEnum.All)
public class AllDataHandler extends TopicTypeHandler
{
    private final TemperatureInfoService temperatureInfoService;
    private final LightInfoService lightInfoService;
    private final TransportCabinService transportCabinService;
    private final DeviceStatusService deviceStatusService;

    @Autowired
    public AllDataHandler(TemperatureInfoService temperatureInfoService, LightInfoService lightInfoService, TransportCabinService transportCabinService, DeviceStatusService deviceStatusService) {
        this.temperatureInfoService = temperatureInfoService;
        this.lightInfoService = lightInfoService;
        this.transportCabinService = transportCabinService;
        this.deviceStatusService = deviceStatusService;
    }


    @Override
    public void handler(String messageContent) {
        ReceivedDataDto receivedDataDto = JSON.parseObject(messageContent, ReceivedDataDto.class);
        receivedDataDto.setCabinId(receivedDataDto.getVid());
        TransportCabin transportCabin1 = BeanCopyUtils.copyBean(receivedDataDto, TransportCabin.class);
        TemperatureInfo temperatureInfo1 = BeanCopyUtils.copyBean(receivedDataDto, TemperatureInfo.class);
        LightInfo lightInfo1 = BeanCopyUtils.copyBean(receivedDataDto, LightInfo.class);
        DeviceStatus deviceStatus1 = BeanCopyUtils.copyBean(receivedDataDto,DeviceStatus.class);
        temperatureInfoService.save(temperatureInfo1);
        lightInfoService.save(lightInfo1);
        transportCabinService.save(transportCabin1);
        deviceStatusService.updateById(deviceStatus1);
    }
}
