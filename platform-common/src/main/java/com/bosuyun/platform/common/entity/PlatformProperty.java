package com.bosuyun.platform.common.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created by liuyuancheng on 2021/6/29  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "key")
})
public class PlatformProperty extends PanacheEntity implements Serializable {

    private String key;

    @ApiModelProperty(value = "实际值")
    private String value;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "注释")
    private String comment;

    public static Optional<PlatformProperty> findByKey(String key) {
        return find("key", key).firstResultOptional();
    }
}
