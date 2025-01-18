package com.it.pkj.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName partymember
 */
@TableName(value ="partymember")
@Data
public class PartyMember implements Serializable {
    /**
     * 党员编号（唯一标识党员，主键）
     */
    @TableId
    private Long partyMemberId;

    /**
     * 姓名
     */
    private String partyMemberName;

    /**
     * 性别
     */
    private String partyMemberGender;

    /**
     * 联系方式
     */
    private String partyMemberContactInformation;

    /**
     * 入党时间
     */
    private String partyMemberJoiningDate;

    /**
     * 地区编号（与地区实体相关联）
     */
    private Long partyMemberRegionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}