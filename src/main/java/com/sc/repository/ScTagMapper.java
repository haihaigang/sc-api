package com.sc.repository;

import java.util.List;

import com.sc.domain.ScTag;
import com.sc.repository.example.ScTagExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScTagMapper {
    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    long countByExample(ScTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByExample(ScTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int insert(ScTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int insertSelective(ScTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    List<ScTag> selectByExample(ScTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    ScTag selectByPrimaryKey(Long id);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExampleSelective(@Param("record") ScTag record, @Param("example") ScTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByExample(@Param("record") ScTag record, @Param("example") ScTagExample example);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKeySelective(ScTag record);

    /**
     *  RudderFramework框架生成代码，请不要直接修改..
     *  sc_tag
     * @generated 2017年12月27日 04:27:17
     */
    int updateByPrimaryKey(ScTag record);
}