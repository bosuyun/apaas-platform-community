package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@Accessors(chain = true)
public class Application extends BaseEntity {

    @ApiModelProperty(value = "应用名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value = "所属工作区")
    @OneToOne(fetch = FetchType.LAZY)
    private Space space;

    @ApiModelProperty(value = "父应用")
    @ManyToOne(fetch = FetchType.LAZY)
    private Application parent;

    @ApiModelProperty(value = "子应用")
    @OneToMany(mappedBy = "parent")
    private List<Application> children;

    @ApiModelProperty(value = "排序")
    @Column(columnDefinition = "int4 DEFAULT '0'", nullable = false)
    private Integer sort;

    @ApiModelProperty(value = "菜单类型 1-内部应用 2-外部链接 3-定制应用 4-目录")
    @Column(nullable = false,columnDefinition = "int2 DEFAULT '0'")
    public Integer type;

    @ApiModelProperty(value = "是否展示菜单")
    @Column(nullable = false,columnDefinition = "boolean DEFAULT 'true'")
    public Boolean showMenu;

    @ApiModelProperty(value = "目录级别")
    @Column(columnDefinition = "int2 DEFAULT '0'")
    private Integer dirLevel;

    @ApiModelProperty(value = "列表页")
    @OneToOne
    private ElementTable tableSchema;

    @ApiModelProperty(value = "应用状态：可用，不可用")
    @Column(columnDefinition = "boolean DEFAULT 'true'")
    private Boolean state;

    @ApiModelProperty(value = "记录数")
    @Column(columnDefinition = "int8 DEFAULT 0")
    private Long records;

    @ApiModelProperty(value = "表单")
    @OneToOne
    private ElementForm formSchema;
}
