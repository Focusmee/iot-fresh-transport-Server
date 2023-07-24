package com.example.iotfreshtransportserver.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (DeviceControl)表实体类
 *
 * @author makejava
 * @since 2023-07-22 21:44:43
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("device_control")
//设备控制信息
public class DeviceControl  {
    @TableId
    private Integer id;

    
    private Integer cabinId;
    
    private Double bright;
    
    private Double speedm1;
    
    private Double speedm2;



}

