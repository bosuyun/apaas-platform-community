package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
//公司")
@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}), // 企业名称不能重复
})
@Accessors(chain = true)
public class Corp extends BaseEntity {

    //企业名称")
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(fetch=FetchType.EAGER)
    private Set<Tenant> spaces;
}
