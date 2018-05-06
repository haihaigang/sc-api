package com.sc.domain;

import javax.validation.constraints.Size;

import com.sc.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * RudderFramework框架自动生成，不允许修改！
 * 表 sc_device_signup
 * @generated do_not_delete_during_merge 2017年12月13日 12:17:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScDeviceSignup extends AbstractEntity<Long> {

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备ID","fieldType":"Object","visible":true,"queryType":3,"displayOrder":0,"length":0,"ref":{"module":"ScDevice","field":"deviceName","type":"SingleList"},"valid":true}
     * @generated 2017年12月13日 12:17:47
     */
    private Long deviceId;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"手机号码","fieldType":"String","visible":true,"checkName":false,"queryType":2,"displayOrder”:0,”length":16,"types":[],"valid":true}
     * @generated 2017年12月13日 12:17:47
     */
    @Size(max=0,message = "手机号码长度无效")
    private String phoneNumber;

}