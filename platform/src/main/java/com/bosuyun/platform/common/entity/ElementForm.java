package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 支持表单、物联网、MQTT/API等多种方式输入
 * <p>
 * Created by liuyuancheng on 2020/9/10  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
@Data
@Accessors(chain = true)
public class ElementForm extends BaseEntity {

    @ApiModelProperty(value = "应用名称")
    @Column(length = 100, nullable = false)
    private String name;

    @ApiModelProperty(value = "当前生效的数据Schema ID")
    @OneToOne(fetch = FetchType.LAZY)
    private DataSchema dataSchema;

    @ApiModelProperty(value = "通过datasource确定数据目标")
    @OneToOne(fetch = FetchType.LAZY)
    private Datasource datasource;

    @ApiModelProperty(value = "数据集合")
    @Column(length = 100)
    private String collection;

}
