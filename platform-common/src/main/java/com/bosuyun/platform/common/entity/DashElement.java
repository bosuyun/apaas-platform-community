package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;


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
//导航版")
@Table
public class DashElement extends BaseEntity {

    //块名称")
    @Column(nullable = false,length = 100)
    private String name;

    //导航块类型 1-列表 2-图表 3-表单")
    private Integer type;

    //导航快")
    @ManyToOne
    private Dashboard dashBlock;

    //每个应用可以有一个dashboard")
    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

}
