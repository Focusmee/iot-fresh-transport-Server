package com.example.iotfreshtransportserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.command.PublishCommand;
import com.example.iotfreshtransportserver.domain.dto.PublishCommandDto;
import com.example.iotfreshtransportserver.domain.vo.PublishCommandVo;
import com.example.iotfreshtransportserver.mqtt.MqttMessageService;
import com.example.iotfreshtransportserver.service.ControlParametersService;
import com.example.iotfreshtransportserver.service.PublishCommandService;
import com.example.iotfreshtransportserver.service.RemoteControlCommandService;
import com.example.iotfreshtransportserver.utils.DateUtils;
import net.minidev.json.JSONUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/publish")
public class PublishController {

    private final MqttMessageService mqttMessageService;

    private final PublishCommandService publishCommandService;
    private static String topic ;

    @Autowired
    public PublishController(MqttMessageService mqttMessageService,PublishCommandService publishCommandService) {
        this.mqttMessageService = mqttMessageService;
        this.publishCommandService = publishCommandService;
        topic = "mqtt/control/command";
    }


    /**
     * mqtt发布
     * 注意这里不能直接@RequstBody PublishCommandDto publishCommandDto 前端传的有float和long不能直接转换注入到参数中
     * @param jsonString json字符串
     * @return {@link ResponseResult}
     */
    @PostMapping("/parameters")
    public ResponseResult publishMqtt(@RequestBody String jsonString) {
        PublishCommandDto publishCommandDto = JSON.parseObject(jsonString, PublishCommandDto.class);
        PublishCommand publishCommand = new PublishCommand();
        BeanUtils.copyProperties(publishCommandDto,publishCommand);
        publishCommand.setTime(LocalDateTime.now());
        publishCommand.setTBegin(DateUtils.longToLocalDateTime(publishCommandDto.getTBegin()));
        publishCommand.setTEnd(DateUtils.longToLocalDateTime(publishCommandDto.getTEnd()));
        //格式化一下
//        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        PublishCommandVo publishCommandVo = new PublishCommandVo();
        BeanUtils.copyProperties(publishCommandDto,publishCommandVo);
        publishCommandVo.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        publishCommandVo.setTBegin(publishCommand.getTBegin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        publishCommandVo.setTEnd(publishCommand.getTEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        mqttMessageService.publish(topic,publishCommandVo);
        publishCommandService.save(publishCommand);//保存发布的命令
        return ResponseResult.okResult();
    }


    @GetMapping("/parameters/{vid}")
    public ResponseResult getControlParameters(@PathVariable Integer vid) {
        PublishCommand byId = publishCommandService.getNewByVid(vid);
        return ResponseResult.okResult(byId);
    }
}
