package com.example.iotfreshtransportserver.domain.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (TransportCabin)表实体类
 *
 * @author makejava
 * @since 2023-07-22 21:44:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("transport_cabin")
//储运厢信息
public class TransportCabin  {
    @TableId
    private Integer id;

    
    private Integer vid;
    
    private Integer pid;

    private LocalDateTime time;

}

