package com.sc.domain;

import javax.validation.constraints.Size;

import com.sc.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * RudderFramework框架自动生成，不允许修改！
 * 表 sc_device
 * @generated do_not_delete_during_merge 2017年12月27日 04:19:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScDevice extends AbstractEntity<Long> {
    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"ID","fieldType":"Id","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    private Long id;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备名称","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @NotEmpty(message="设备名称不能为空")
    @Size(max=0,message = "设备名称长度无效")

    private String deviceName;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备厂家","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备厂家长度无效")

    private String vendor;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备型号","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备型号长度无效")

    private String model;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备图片","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备图片长度无效")

    private String image;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备安装位置","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备安装位置长度无效")

    private String installLocation;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备配置","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备配置长度无效")

    private String config;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备类型","fieldType":"Enum","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[{"label":"门禁","value":"ACCESS"},{"label":"视频","value":"CAMERA"}],"valid":true}
     * @generated 2017年12月27日 04:19:32
     */
    @Size(max=0,message = "设备类型长度无效")

    private String type;

    /**
     *
     * {"name":"是否显示在首页","fieldType":"Bool","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":false}
     * @generated 2017年12月27日 04:19:32
     */
    private Boolean forFront;

}