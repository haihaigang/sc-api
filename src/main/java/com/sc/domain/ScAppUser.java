package com.sc.domain;

import javax.validation.constraints.Size;

import com.sc.domain.base.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * RudderFramework框架自动生成，不允许修改！
 * 表 sc_app_user
 * @generated do_not_delete_during_merge 2017年12月27日 04:27:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScAppUser extends AbstractEntity<Long> {

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"用户名","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */

    private String name;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"手机号码","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */

    private String phoneNumber;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"开发者编号","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */

    private String develoerCode;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"社区编码","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String tenantCode;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"代理用户名","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String proxyUser;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"对讲服务器IP","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String httpServer;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云用户唯一标识","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smUnid;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云用户名","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    @Size(max=0,message = "市民云用户名长度无效")

    private String smUserName;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云登录用户名","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smUserLoginName;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云用户类型","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smUserType;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云证件号码","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smCertificateNumber;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云性别","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smSex;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云手机号","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smMobilePhone;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云地址","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smAddress;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云邮箱","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smEmail;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云姓名","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smName;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云最后更新时间","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smLastUpdatetime;

    /**
     *
     * {"viewconfig":{"optype":"1","formid":"1"},"name":"市民云证件类型","fieldType":"String","visible":true,"queryType":0,"displayOrder":0,"length":0,"types":[],"valid":true}
     * @generated 2017年12月27日 04:27:17
     */
    private String smCertificateType;

}