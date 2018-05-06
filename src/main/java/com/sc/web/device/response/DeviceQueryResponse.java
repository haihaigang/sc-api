package com.sc.web.device.response;

import lombok.Data;

/**
 * Created by hgwang on 2017/12/28.
 */
@Data
public class DeviceQueryResponse {
    /**
     *
     * ID
     */
    private Long id;

    /**
     *
     * 设备名称
     */
    private String deviceName;

    /**
     *
     * 设备厂家
     */
    private String vendor;

    /**
     *
     * 设备型号
     */
    private String model;

    /**
     *
     * 设备图片
     */
    private String image;

    /**
     *
     * 设备安装位置
     */
    private String installLocation;

    /**
     *
     * 设备配置
     */
    private String config;

    private String type;

    private Boolean forFront;

    private Long tagId;
}
