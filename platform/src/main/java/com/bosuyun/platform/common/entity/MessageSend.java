package com.bosuyun.platform.common.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 站内信
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class MessageSend extends PanacheEntity implements Serializable {

    @ApiModelProperty(value = "消息体")
    @ManyToOne(fetch = FetchType.EAGER)
    private Message message;

    @ApiModelProperty(value = "消息接收人")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member toMember;

    @ApiModelProperty(value = "消息发送人 0-为系统消息")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member fromMember;

    @ApiModelProperty(value = "是否已被查看")
    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private Boolean viewed;

    @ApiModelProperty(value = "接收时间")
    @Column(columnDefinition = "timestamptz DEFAULT NOW()", updatable = false)
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "查看时间")
    @Column(insertable = false)
    private LocalDateTime viewedAt;
}
