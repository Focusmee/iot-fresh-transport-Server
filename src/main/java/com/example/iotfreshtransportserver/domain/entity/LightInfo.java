package com.example.iotfreshtransportserver.domain.entity;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import java.io.Serializable;
import java.util.Date;

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

    
    private Integer cabinId;
    
    private Double lxin;
    
    private Double lxd;
    
    private LocalTime tbegin;
    
    private LocalTime tend;

    private Date time;

    public static String formatDate(Date date) {
        // 创建一个 SimpleDateFormat 对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        // 使用 SimpleDateFormat 格式化日期时间为字符串
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

}

