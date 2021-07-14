package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.context.ReqContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DeleteStatement extends SqlStatement {

    public WhereClause whereClause;

    public DeleteStatement(ReqContext context) {
        this.setTableName(context.getTableName()).setTableSchema(context.getTableSchema());
    }

    @Override
    public String toSql() {
        return null;
    }

}
