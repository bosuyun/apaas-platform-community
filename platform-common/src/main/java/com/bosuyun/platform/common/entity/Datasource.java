package com.bosuyun.platform.common.entity;

import com.bosuyun.platform.common.BaseEntity;
import com.bosuyun.platform.common.definition.DSDriverEnum;
import com.bosuyun.platform.common.exception.BizzException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

/**
 * 分散数据，有助于减少数据库压力。
 * <p>
 * Created by liuyuancheng on 2020/12/11  <br/>
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table
@Slf4j
public class Datasource extends BaseEntity {

    private static final Cache<Long, Datasource> dsCache = CacheBuilder
            .newBuilder()
            .initialCapacity(1)
            .maximumSize(100)
            .build();

    public static Datasource findByIdFromCache(long id) {
        Datasource dataSource = dsCache.getIfPresent(id);
        if (Objects.isNull(dataSource)) {
            log.debug("datasource id {} notfound in memory cache, will find in in database.", id);
            dataSource = findById(id);
            if (Objects.isNull(dataSource)) {
                throw new BizzException(String.format("datasource id %s is not available.", id));
            }
            dsCache.put(id, dataSource);
        }
        return dataSource;
    }

    //数据源名称")
    @Column(length = 100, nullable = false)
    private String name;

    //数据库驱动
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private DSDriverEnum driver;

    //主机名")
    @Column(nullable = false)
    private String host;

    //端口号")
    @Column(nullable = false)
    private Integer port;

    //    //用户名")
    @Column(nullable = false)
    private String username;

    //    //密码")
    @Column(nullable = false)
    private String password;

    //    //数据库名称：自动加上前缀 data_engine_")
    @Column(length = 100, nullable = false)
    private String dbname;

    //    //当前状态是否可用")
    @Column(nullable = false, columnDefinition = "boolean default 'false'")
    private Boolean available;

    //    //连接池")
    @Column
    private Integer poolSize;


}
