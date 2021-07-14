package com.bosuyun.platform.data.driver.query.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class HavingClause extends SqlClause {

    @Override
    public String toSqlSegment() {
        return null;
    }
}
