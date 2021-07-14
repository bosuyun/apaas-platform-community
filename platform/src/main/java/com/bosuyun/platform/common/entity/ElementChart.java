package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * 自定义图标，支持条件语句
 *
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "图表")
@Entity
public class ElementChart extends BaseEntity {

    @ApiModelProperty(value = "图表名称")
    @Column(nullable = false)
    private String name;

    @OneToOne
    private DataSchema dataSchema;

}
