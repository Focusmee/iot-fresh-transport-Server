package com.example.iotfreshtransportserver.domain.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (LightInfo)表实体类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("light_info")
//光照信息
public class LightInfo  {
    @TableId
    private Integer id;

    @TableField("cabinId")
    private Integer cabinId;
    
    private Double lxin;
    
    private Double lxd;
    
    private LocalDateTime tbegin;
    
    private LocalDateTime tend;

    private LocalDateTime time;

    public static String formatDate(LocalDateTime date) {
        // 创建一个 SimpleDateFormat 对象，指定日期时间格式
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");

        // 使用 SimpleDateFormat 格式化日期时间为字符串
        String formattedDate = df.format(date);

        return formattedDate;
    }

}

