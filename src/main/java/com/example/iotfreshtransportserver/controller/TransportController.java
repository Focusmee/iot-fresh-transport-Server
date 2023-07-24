package com.example.iotfreshtransportserver.controller;

import com.example.iotfreshtransportserver.domain.ResponseResult;
import com.example.iotfreshtransportserver.domain.entity.*;
import com.example.iotfreshtransportserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/transport")
public class TransportController {

    @Autowired
    private TransportCabinService transportCabinService;

    @Autowired
    private TemperatureInfoService temperatureInfoService;

    @Autowired
    private LightInfoService lightInfoService;

    @Autowired
    private DeviceControlService deviceControlService;

    @Autowired
    private DeviceStatusService deviceStatusService;

    // 在此处添加各种请求处理方法
    /**
     * 获取储运厢信息
     * @param vid 储运厢编号
     * @return 储运厢信息
     */
    @GetMapping("/{vid}")
    public ResponseResult<TransportCabin> getTransportCabin(@PathVariable("vid") String vid) {
        TransportCabin transportCabin = transportCabinService.getTransportCabinByVID(vid);
        return ResponseResult.okResult(transportCabin);
    }

    /**
     * 添加储运厢信息
     * @param vid 储运厢编号
     * @param pid 物品识别号
     */
    @PostMapping("/{vid}")
    public ResponseResult<Void> addTransportCabin(@PathVariable("vid") String vid, @RequestParam("pid") String pid) {
        transportCabinService.addTransportCabin(vid, pid);
        return ResponseResult.okResult();
    }

    /**
     * 更新储运厢信息
     * @param vid 储运厢编号
     * @param pid 物品识别号
     */
    @PutMapping("/{vid}")
    public ResponseResult<Void> updateTransportCabin(@PathVariable("vid") String vid, @RequestParam("pid") String pid) {
        transportCabinService.updateTransportCabin(vid, pid);
        return ResponseResult.okResult();
    }

    /**
     * 删除储运厢信息
     * @param vid 储运厢编号
     */
    @DeleteMapping("/{vid}")
    public ResponseResult<Void> deleteTransportCabin(@PathVariable("vid") String vid) {
        transportCabinService.deleteTransportCabinByVID(vid);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢温度信息
     * @param vid 储运厢编号
     * @return 储运厢温度信息
     */
    @GetMapping("/{vid}/temperature")
    public ResponseResult<TemperatureInfo> getTemperatureInfo(@PathVariable("vid") String vid) {
        TemperatureInfo temperatureInfo = temperatureInfoService.getTemperatureInfoByVID(vid);
        return ResponseResult.okResult(temperatureInfo);
    }

    /**
     * 更新储运厢温度信息
     * @param vid 储运厢编号
     * @param tin 储运厢内部实时温度
     * @param tout 储运厢外部实时温度
     * @param tinDH 储运厢内部控制设定的上限温度
     * @param tinDL 储运厢内部控制设定的下限温度
     * @param tg 储运厢内、厢外节能控温设定的可利用温差
     */
    @PutMapping("/{vid}/temperature")
    public ResponseResult<Void> updateTemperatureInfo(@PathVariable("vid") String vid,
                                                      @RequestParam("tin") double tin,
                                                      @RequestParam("tout") double tout,
                                                      @RequestParam("tinDH") double tinDH,
                                                      @RequestParam("tinDL") double tinDL,
                                                      @RequestParam("tg") double tg) {
        temperatureInfoService.updateTemperatureInfo(vid, tin, tout, tinDH, tinDL, tg);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢光照信息
     * @param vid 储运厢编号
     * @return 储运厢光照信息
     */
    @GetMapping("/{vid}/light")
    public ResponseResult<LightInfo> getLightInfo(@PathVariable("vid") String vid) {
        LightInfo lightInfo = lightInfoService.getLightInfoByVID(vid);
        return ResponseResult.okResult(lightInfo);
    }

    /**
     * 更新储运厢光照信息
     * @param vid 储运厢编号
     * @param lxin 储运厢内实时光照
     * @param lxd 储运厢内设定的光照阈值
     * @param tbegin 储运厢光照控制时间段的起始时间
     * @param tend 储运厢光照控制时间段的终止时间
     */
    @PutMapping("/{vid}/light")
    public ResponseResult<Void> updateLightInfo(@PathVariable("vid") String vid,
                                                @RequestParam("lxin") double lxin,
                                                @RequestParam("lxd") double lxd,
                                                @RequestParam("tbegin") String tbegin,
                                                @RequestParam("tend") String tend) {
        lightInfoService.updateLightInfo(vid, lxin, lxd, tbegin, tend);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢设备控制信息
     * @param vid 储运厢编号
     * @return 储运厢设备控制信息
     */
    @GetMapping("/{vid}/control")
    public ResponseResult<DeviceControl> getDeviceControl(@PathVariable("vid") String vid) {
        DeviceControl deviceControl = deviceControlService.getDeviceControlByVID(vid);
        return ResponseResult.okResult(deviceControl);
    }


    /**
     * 更新储运厢设备控制信息
     * @param vid 储运厢编号
     * @param bright 发光器件发光功率控制值
     * @param speedM1 压缩机速度控制值
     * @param speedM2 风机速度控制值
     */
    @PutMapping("/{vid}/control")
    public ResponseResult<Void> updateDeviceControl(@PathVariable("vid") String vid,
                                                    @RequestParam("bright") double bright,
                                                    @RequestParam("speedM1") double speedM1,
                                                    @RequestParam("speedM2") double speedM2) {
        deviceControlService.updateDeviceControl(vid, bright, speedM1, speedM2);
        return ResponseResult.okResult();
    }

    /**
     * 获取储运厢设备状态信息
     * @param vid 储运厢编号
     * @return 储运厢设备状态信息
     */
    @GetMapping("/{vid}/status")
    public ResponseResult<DeviceStatus> getDeviceStatus(@PathVariable("vid") String vid) {
        DeviceStatus deviceStatus = deviceStatusService.getDeviceStatusByVID(vid);
        return ResponseResult.okResult(deviceStatus);
    }

}
