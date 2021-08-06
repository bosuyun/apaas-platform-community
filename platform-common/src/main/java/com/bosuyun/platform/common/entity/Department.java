package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import com.bosuyun.platform.common.utils.StringLUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * 部门
 * <p>
 * Created by liuyuancheng on 2020/8/14  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(indexes = {
        @Index(name = "idx_level", columnList = "level")
})
public class Department extends BaseEntity {

    //部门名称")
    @Column(nullable = false)
    private String name;

    //以斜线分隔的部门名称")
    @Column(nullable = false)
    private String fullNameWithSlash;

    //斜线分隔的ID")
    @Column(nullable = false, updatable = false)
    private String idChainWithSlash;

    //父部门")
    @ManyToOne
    private Department parent;

    //子部门")
    @OneToMany(mappedBy = "parent")
    private List<Department> children;

    //分层级别")
    @Column(nullable = false, updatable = false)
    private Integer level;

}
