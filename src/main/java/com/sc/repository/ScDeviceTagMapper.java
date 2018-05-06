package com.sc.repository;

import java.util.List;

import com.sc.domain.ScDeviceTag;
import com.sc.repository.example.ScDeviceTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScDeviceTagMapper {
    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    long countByExample(ScDeviceTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByExample(ScDeviceTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int insert(ScDeviceTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int insertSelective(ScDeviceTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    List<ScDeviceTag> selectByExample(ScDeviceTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    ScDeviceTag selectByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExampleSelective(@Param("record") ScDeviceTag record, @Param("example") ScDeviceTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExample(@Param("record") ScDeviceTag record, @Param("example") ScDeviceTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKeySelective(ScDeviceTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKey(ScDeviceTag record);
}