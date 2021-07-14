package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by liuyuancheng on 2021/6/16  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(indexes = {
        @Index(name = "idx_key", columnList = "key", unique = true)
})
public class Role extends BaseEntity {

    @ApiModelProperty(value = "归属企业")
    @ManyToOne(fetch = FetchType.LAZY)
    private Corp corp;

    @ApiModelProperty(value = "角色组")
    @ManyToOne(fetch = FetchType.LAZY)
    private Role parent;

    @ApiModelProperty(value = "父级角色")
    @OneToMany(mappedBy = "parent")
    private Set<Role> children;

    @ApiModelProperty(value = "角色Key", example = "user.manage.add")
    @Column(nullable = false)
    private String key;

    @ApiModelProperty(value = "角色组名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value = "成员")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Member> members;
}
