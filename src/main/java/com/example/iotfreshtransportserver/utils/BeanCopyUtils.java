package com.example.iotfreshtransportserver.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    //参数确定：不方便的点在于我每次要创建vo对象 所以就只需要获得需要创建什么类型的对象就知道怎么创建了
    //我希望返回的就是vo对象，所以使用泛型，在clazz传递进来的时候我就已经确定好返回的类型了以避免强制转换带来的问题
    public static <V> V copyBean(Object source,Class<V> clazz){
        //创建目标对象
        V result = null;
        try {
            result= clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    //我希望传进来的字节码,注意，在方法名前加上 <T> 表示该方法是一个泛型方法。
    public static <O,V>List<V> copyBeanList(List<O> list,Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
