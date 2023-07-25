package com.example.iotfreshtransportserver.controller;

import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.command.ControlParameter;
import com.example.iotfreshtransportserver.mqtt.MqttMessageService;
import com.example.iotfreshtransportserver.service.ControlParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device/parameter")
public class ParameterController {
    private final ControlParametersService controlParameterService;
    private final MqttMessageService mqttMessageService;

    private static String topic ;

    @Autowired
    public ParameterController(ControlParametersService controlParameterService, MqttMessageService mqttMessageService) {
        this.controlParameterService = controlParameterService;
        this.mqttMessageService = mqttMessageService;
        this.topic = "mqtt/control/parameter";
    }

    /**
     * 保存并发送控制参数
     *
     * @param parameter 参数
     * @return {@link ResponseResult}
     */// Endpoint to handle saving a new control parameter
    @PostMapping("/save")
    public ResponseResult saveControlParameter(@RequestBody ControlParameter parameter) {
        controlParameterService.saveOrUpdate(parameter);
        mqttMessageService.publish(topic,parameter);
        return ResponseResult.okResult();
    }

    /**
     * 检查控制参数
     *
     * @return {@link ResponseResult}
     */
    @GetMapping("/check")
    public ResponseResult checkControlParameter() {
        List<ControlParameter> list = controlParameterService.list();
        if (list.isEmpty()) {
            return ResponseResult.okResult(null);
        }
        return ResponseResult.okResult(list.get(0));
    }

}
