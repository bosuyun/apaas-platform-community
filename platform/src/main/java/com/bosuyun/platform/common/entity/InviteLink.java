package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by liuyuancheng on 2021/6/16  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(indexes = {
}, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
public class InviteLink extends BaseEntity {

    @ApiModelProperty(value = "易读的Code，随机纯字符")
    @Column(nullable = false)
    private String code;

    @ApiModelProperty(value = "部门")
    @ManyToOne
    private Department department;

    @ApiModelProperty(value = "所属企业")
    @ManyToOne
    private Corp corp;
}
