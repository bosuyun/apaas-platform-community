package com.bosuyun.platform.common.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 站内信，消息内容
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Message extends PanacheEntity implements Serializable {

    @Column(nullable = false)
    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息类型 0-未定义 1-系统通知 2-用户消息 3-应用消息 4-个人待办")
    @Column(nullable = false, columnDefinition = "int2 DEFAULT '0'")
    private Integer type;

}
