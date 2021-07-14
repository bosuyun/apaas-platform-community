package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNodeList;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 使用时序数据库引擎
 *
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "无界时间日志（MQ异步写）")
@Entity
@Table
@Data
public class UnifiedTrace extends PanacheEntity implements Serializable {

    @ApiModelProperty(value = "上下文")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ReqContext context;

    @ApiModelProperty(value = "来源类型 1-cron 2-DataOp 3-接口请求")
    private Integer eventType;

    @ApiModelProperty(value = "请求内容")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private DataNodeList content;

    @ApiModelProperty(value = "目标类型 1-数据表修改 2-MQTT-IOT 3-MQTT-Web")
    private Integer targetType;

}
