package com.example.iotfreshtransportserver.handler.anno;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.example.iotfreshtransportserver.enums.TopicTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TopicTypeHandlerAnno {
    TopicTypeEnum value();
}
