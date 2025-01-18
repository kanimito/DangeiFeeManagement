package com.it.pkj.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName paymentrecord
 */
@TableName(value ="paymentrecord")
@Data
public class PaymentRecord implements Serializable {
    /**
     * 缴费记录编号（唯一标识缴费记录，主键）
     */
    @TableId
    private Long paymentRecordId;

    /**
     * 党员编号（外键，关联党员实体）
     */
    private Long partyMemberId;

    /**
     * 缴费时间
     */
    private String paymentTime;

    /**
     * 缴费金额
     */
    private Double paymentAmount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}