package com.example.iotfreshtransportserver.domain.command;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (ControlParameters)表实体类
 *
 * @author makejava
 * @since 2023-07-24 19:39:17
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("control_parameter")
public class ControlParameter  {
    @TableId
    private Integer id;

    
    private Integer cabinId;
    
    private String parameterType;
    
    private Double parameterValue;



}

