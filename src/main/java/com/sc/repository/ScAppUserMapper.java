package com.sc.repository;

import java.util.List;

import com.sc.domain.ScAppUser;
import com.sc.repository.example.ScAppUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScAppUserMapper {
    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    long countByExample(ScAppUserExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByExample(ScAppUserExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int insert(ScAppUser record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int insertSelective(ScAppUser record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    List<ScAppUser> selectByExample(ScAppUserExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    ScAppUser selectByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExampleSelective(@Param("record") ScAppUser record, @Param("example") ScAppUserExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExample(@Param("record") ScAppUser record, @Param("example") ScAppUserExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKeySelective(ScAppUser record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_app_user
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKey(ScAppUser record);
}