package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 仪表盘
 * <p>
 * Created by liuyuancheng on 2020/9/11  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@ApiModel(description = "导航版")
@Table
public class Dashboard extends BaseEntity {

    @ApiModelProperty(value = "导航名称")
    @Column(nullable = false,length = 100)
    private String name;

    @ApiModelProperty(value = "每个应用可以有一个dashboard")
    @OneToOne
    private Application application;
}
