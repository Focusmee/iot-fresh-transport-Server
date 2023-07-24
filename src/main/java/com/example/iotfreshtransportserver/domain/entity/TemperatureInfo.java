package com.example.iotfreshtransportserver.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (TemperatureInfo)表实体类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("temperature_info")
//温度信息
public class TemperatureInfo  {
    @TableId
    private Integer id;

    
    private Integer cabinId;
    
    private Double tin;
    
    private Double tout;
    
    private Double tindh;
    
    private Double tindl;
    
    private Double tg;



}

