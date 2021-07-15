package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.data.driver.query.SqlOperatorFactory;
import com.bosuyun.platform.data.driver.query.sql.WhereClause;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@QuarkusTest
class WhereClauseTest {

    @Test
    void single() {
        WhereClause whereClause = SqlOperatorFactory.id(11L).ne("age",1).setLogicalOr();
        System.out.println(whereClause.toSqlSegment());
    }

    @Test
    void testEq() {
        WhereClause whereClause = SqlOperatorFactory.emptyClause()
                .addOrClause(SqlOperatorFactory.eq("name", "111").id(6876L))
                .addClause(SqlOperatorFactory.in("class", Arrays.asList("1", 3, 4)).eq("ddd", 1))
                .addClause(SqlOperatorFactory.id(11L).ne("ggs", "ggs1"))
                .addOrClause(SqlOperatorFactory.between("date", 1, 3).eq("name", "aaa"))
                .setLogicalAnd();
        System.out.println(whereClause.toSqlSegment());
    }

}