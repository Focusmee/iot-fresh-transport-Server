package com.example.iotfreshtransportserver.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (VstatusDescription)表实体类
 *
 * @author makejava
 * @since 2023-07-31 09:55:10
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vstatus_description")
public class VstatusDescription  {
    @TableId
    private Integer id;

    
    private String description;



}

