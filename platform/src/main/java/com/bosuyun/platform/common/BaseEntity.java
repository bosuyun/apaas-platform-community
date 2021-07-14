package com.bosuyun.platform.common;

import com.bosuyun.platform.common.utils.JsonUtils;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class BaseEntity extends PanacheEntity implements Serializable {

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ApiModelProperty(value = "上次更新时间")
    @Column(nullable = false, updatable = false, columnDefinition = "timestamptz DEFAULT NOW()")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ApiModelProperty(value = "上次更新时间")
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "软删除标记")
    @Column(columnDefinition = "boolean DEFAULT 'false'", nullable = false, insertable = false)
    private Boolean deleted;

    @ApiModelProperty(value = "软删除时间")
    @Column(insertable = false)
    private LocalDateTime deletedAt;

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }
}
