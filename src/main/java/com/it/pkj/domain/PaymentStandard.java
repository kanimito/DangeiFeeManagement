package com.it.pkj.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName paymentstandard
 */
@TableName(value ="paymentstandard")
@Data
public class PaymentStandard implements Serializable {
    /**
     * 标准编号（唯一标识党费标准，主键）
     */
    @TableId
    private Long standardId;

    /**
     * 标准金额
     */
    private Double standardAmount;

    /**
     * 计算方式（1、固定金额；2、工资比例）
     */
    private Integer calculationMethod;

    /**
     * 缴纳截止日期
     */
    private String dueDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}