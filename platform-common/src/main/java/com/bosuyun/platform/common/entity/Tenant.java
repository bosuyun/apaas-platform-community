package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * 工作区
 *
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name","corp_id"}),// 组织下，不能出现同名工作区
})
@Data
@Accessors(chain = true)
public class Tenant extends BaseEntity {

    @ApiModelProperty(value = "租户名称")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value = "所属企业")
    @ManyToOne(fetch = FetchType.EAGER)
    private Corp corp;

    @Override
    public String toString(){
        return toJsonString(this);
    }
}
