package com.example.iotfreshtransportserver.enums;

public enum TopicTypeEnum {
    Temperature("1", "mqtt/TemperatureInfo"),
    Light("2", "mqtt/LightInfo"),
    Transport("3", "mqtt/TransportCabin"),
    All("4","topic1");
    private String code;    //代码
    private String name;    //名称，描述

    TopicTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据code属性获取name属性
     *
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        for (TopicTypeEnum temp : TopicTypeEnum.values()) {
            if (temp.getCode().equals(code)) {
                return temp.getName();
            }
        }
        return null;
    }
}
