package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 定义图标
 *
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table
@Entity
@ApiModel(value = "列表")
public class ElementTable extends BaseEntity {

    @ApiModelProperty(value = "列表名称")
    @Column(nullable = false)
    private String name;

    @OneToOne
    private DataSchema dataSchema;

}
