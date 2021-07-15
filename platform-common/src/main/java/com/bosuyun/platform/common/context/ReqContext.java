package com.bosuyun.platform.common.context;


import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 当前操作的上下文
 * <p>
 * Created by liuyuancheng on 2020/9/11  <br/>
 */
@Data
@Accessors(chain = true)
public class ReqContext {

    /**
     * 请求ID
     */
    private String traceId;

    /**
     * 工作区/租户ID (与pg tableSchema对应)
     * tenantId is used as a schema, a company can have many tenant
     */
    private Long tenantId;

    /**
     * 数据应用ID
     */
    private Long applicationId;

    /**
     * 用户session
     */
    private String sessionId;

    /**
     * 数据源配置ID
     */
    private Long dsId;

    /**
     * 操作的数据集合
     */
    private String tableName;

    /**
     * 数据结构ID
     */
    private Long schemaId;

    /**
     * 请求发起时间
     */
    private LocalDateTime launchAt;

    /**
     * 生成新的跟踪ID
     */
    public void newTraceId() {
        this.traceId = UUID.randomUUID().toString();
    }

    public String getTableSchema() {
        return "spc" + this.getTenantId();
    }

    /**
     * 请求的ID
     *
     * @return
     */
    public String getTableNameChain() {
        return getTableSchema() + "." + this.getTableName();
    }

    /**
     * 将TableNameChain解析
     *
     * @param tableNameChain
     * @return
     */
    public static List<String> parseTableNameChain(String tableNameChain) {
        int split = tableNameChain.indexOf(".");
        var tableSchema = tableNameChain.substring(0, split);
        return Arrays.asList(StringUtils.isBlank(tableSchema) ? "public" : tableSchema, tableNameChain.substring(split + 1));
    }
}
