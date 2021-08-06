package com.bosuyun.platform.common;

import com.bosuyun.platform.common.utils.JsonUtils;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by liuyuancheng on 2020/12/8  <br/>
 */
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //上次更新时间")
    @Column(nullable = false, updatable = false, columnDefinition = "timestamptz DEFAULT NOW()")
    private LocalDateTime createdAt = LocalDateTime.now();

    //上次更新时间")
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    //软删除标记")
    @Column(columnDefinition = "boolean DEFAULT 'false'", nullable = false, insertable = false)
    private Boolean deleted;

    //软删除时间")
    @Column(insertable = false)
    private LocalDateTime deletedAt;

    @Override
    public String toString() {
        return JsonUtils.toJsonString(this);
    }


    public static <T> T findById(long id) {
        return null;
    }

    public static <T> T update(String statement, Object... args) {
        return null;
    }

    public static <T> T find(String statement){
        return null;
    }
}
