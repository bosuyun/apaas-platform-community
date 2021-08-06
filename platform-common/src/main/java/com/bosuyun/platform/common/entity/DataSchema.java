package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.schema.ObjectType;
import com.bosuyun.platform.common.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 应用
 * <p>
 * Created by liuyuancheng on 2020/9/10  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
@Slf4j
public class DataSchema extends BaseEntity {

    @ApiModelProperty(value = "所属工作区")
    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private ObjectType schema;

    @ApiModelProperty(value = "schema版本号，也会保存至appdata")
    private Boolean current;

}
