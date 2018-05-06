package com.sc.domain;

import com.sc.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * RudderFramework框架自动生成，不允许修改！
 * 表 sc_device_tag
 * @generated do_not_delete_during_merge 2017年12月27日 04:27:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScDeviceTag extends AbstractEntity<Long> {

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"设备ID","fieldType":"Object","visible":true,"queryType":3,"displayOrder":0,"length":0,"ref":{"module":"ScDevice","field":"deviceName","type":"SingleList"},"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private Long deviceId;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"标签ID","fieldType":"Object","visible":true,"queryType":3,"displayOrder":0,"length":0,"ref":{"module":"ScTag","field":"name","type":"SingleList"},"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private Long tagId;
}