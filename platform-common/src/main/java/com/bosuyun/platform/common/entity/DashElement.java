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
public class DashElement extends BaseEntity {

    @ApiModelProperty(value = "块名称")
    @Column(nullable = false,length = 100)
    private String name;

    @ApiModelProperty(value = "导航块类型 1-列表 2-图表 3-表单")
    private Integer type;

    @ApiModelProperty(value = "导航快")
    @ManyToOne
    private Dashboard dashBlock;

    @ApiModelProperty(value = "每个应用可以有一个dashboard")
    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

}
