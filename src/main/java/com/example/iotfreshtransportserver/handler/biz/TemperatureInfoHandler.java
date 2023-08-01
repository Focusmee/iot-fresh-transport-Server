package com.example.iotfreshtransportserver.handler.biz;

import com.alibaba.fastjson.JSONObject;
import com.example.iotfreshtransportserver.domain.entity.TemperatureInfo;
import com.example.iotfreshtransportserver.enums.TopicTypeEnum;
import com.example.iotfreshtransportserver.handler.TopicTypeHandler;
import com.example.iotfreshtransportserver.handler.anno.TopicTypeHandlerAnno;
import com.example.iotfreshtransportserver.service.TemperatureInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@TopicTypeHandlerAnno(TopicTypeEnum.Temperature)
public class TemperatureInfoHandler extends TopicTypeHandler {
    private final TemperatureInfoService temperatureInfoService;

    public TemperatureInfoHandler(TemperatureInfoService temperatureInfoService) {
        this.temperatureInfoService = temperatureInfoService;
    }

    @Override
    public void handler(String messageContent) {
        TemperatureInfo temperatureInfo = JSONObject.parseObject(messageContent, TemperatureInfo.class);
        temperatureInfoService.save(temperatureInfo);
    }
}
