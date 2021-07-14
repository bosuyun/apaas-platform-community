package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.definition.DSDriverTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@Data
@Slf4j
@Accessors(chain = true)
public abstract class SqlStatement {

    private StringBuilder sql = new StringBuilder();

    private String tableName;

    private String tableSchema;

    private DSDriverTypeEnum driverType;

    private Long schemaId;

    public void resetSql(){
        this.sql.delete(0,sql.length());
    }

    public abstract String toSql();

}
