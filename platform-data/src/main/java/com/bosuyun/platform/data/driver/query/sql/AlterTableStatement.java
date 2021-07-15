package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.context.ReqContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlterTableStatement extends SqlStatement {

    public AlterTableStatement(ReqContext context) {

    }

    @Override
    public String toSql() {
        return null;
    }
}
