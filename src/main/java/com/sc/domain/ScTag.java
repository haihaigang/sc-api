package com.sc.domain;

import com.sc.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * RudderFramework框架自动生成，不允许修改！
 * 表 sc_tag
 * @generated do_not_delete_during_merge 2017年12月27日 04:27:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScTag extends AbstractEntity<Long> {
    /**
     *
     * {"name":"标签名称","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":false}
     * @generated 2017年12月27日 04:27:17
     */
    private String name;

    /**
     *
     * {"viewconfig":{"optype":"3","formid":"1"},"name":"用户ID","fieldType":"Object","visible":true,"queryType":3,"displayOrder":0,"length":0,"ref":{"module":"ScAppUser","field":"name","type":"SingleList"},"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private Long userId;
}