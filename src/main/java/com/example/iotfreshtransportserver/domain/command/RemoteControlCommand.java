package com.example.iotfreshtransportserver.domain.command;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (RemoteControlCommand)表实体类
 *
 * @author makejava
 * @since 2023-07-24 20:27:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("remote_control_command")
public class RemoteControlCommand  {
    @TableId
    private Integer id;

    
    private Integer cabinId;
    
    private String deviceType;
    
    private String commandType;
    
    private Double commandValue;
    
    private Date commandTime;



}

