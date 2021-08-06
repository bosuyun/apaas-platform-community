package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
public class PlatformProperty extends BaseEntity {

    private String key;

    //实际值")
    private String value;

    //默认值")
    private String defaultValue;

    //注释")
    private String comment;


    public static Optional<PlatformProperty> findByKey(String key) {
        return null;
    }

}
