package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@Accessors(chain = true)
@ApiModel(value = "MQTT客户端")
public class Endpoint extends BaseEntity {

    @ManyToOne
    private Corp corp;

    @ApiModelProperty
    private String name;

}
