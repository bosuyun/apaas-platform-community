package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@Accessors(chain = true)
public class Endpoint extends BaseEntity {

    @ManyToOne
    private Corp corp;

    private String name;

}
