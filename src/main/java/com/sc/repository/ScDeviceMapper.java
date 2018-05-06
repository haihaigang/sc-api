package com.sc.repository;

import java.util.List;

import com.sc.domain.ScDevice;
import com.sc.repository.example.ScDeviceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScDeviceMapper {
    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    long countByExample(ScDeviceExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int deleteByExample(ScDeviceExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int insert(ScDevice record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int insertSelective(ScDevice record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    List<ScDevice> selectByExample(ScDeviceExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    ScDevice selectByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int updateByExampleSelective(@Param("record") ScDevice record, @Param("example") ScDeviceExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int updateByExample(@Param("record") ScDevice record, @Param("example") ScDeviceExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int updateByPrimaryKeySelective(ScDevice record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_device
     * @generated 2017年12月27日 04:19:32
     */
    int updateByPrimaryKey(ScDevice record);
}