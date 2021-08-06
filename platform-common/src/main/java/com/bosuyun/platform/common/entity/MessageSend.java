package com.bosuyun.platform.common.entity;


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
@Data
@Entity
@Table
public class MessageSend implements Serializable {

    //消息体")
    @ManyToOne(fetch = FetchType.EAGER)
    private Message message;

    //消息接收人")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member toMember;

    //消息发送人 0-为系统消息")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member fromMember;

    //是否已被查看")
    @Column(nullable = false, columnDefinition = "boolean DEFAULT 'false'")
    private Boolean viewed;

    //接收时间")
    @Column(columnDefinition = "timestamptz DEFAULT NOW()", updatable = false)
    private LocalDateTime createdAt;

    //查看时间")
    @Column(insertable = false)
    private LocalDateTime viewedAt;
}
