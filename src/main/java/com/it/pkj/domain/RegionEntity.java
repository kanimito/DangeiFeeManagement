package com.it.pkj.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName regionentity
 */
@TableName(value ="regionentity")
@Data
public class RegionEntity implements Serializable {
    /**
     * 地区编号（唯一标识地区，主键）
     */
    @TableId
    private Long regionEntityRegionId;

    /**
     * 地区名称
     */
    private String regionEntityRegionName;

    /**
     * 地区权重
     */
    private Integer regionEntityRegionWeight;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}