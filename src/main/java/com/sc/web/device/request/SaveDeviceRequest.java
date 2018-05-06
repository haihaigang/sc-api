package com.sc.web.device.request;

import lombok.Data;

/**
 * Created by hgwang on 2017/12/28.
 */
@Data
public class SaveDeviceRequest {
//    （名称、图片、是否显示在首页、标签）

    private Long id;
    private String deviceName;
    private String devicePic;
    private Boolean isForFront;
    private Long tagId;
}
