package com.bosuyun.platform.data.driver.executor;

import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodePage;
import com.bosuyun.platform.common.utils.JsonUtils;
import com.bosuyun.platform.TestContext;
import com.bosuyun.platform.common.misc.DataNodeList;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static com.bosuyun.platform.data.driver.query.SqlOperatorFactory.eq;

/**
 * 查询测试
 * <p>
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
@QuarkusTest
@Slf4j
class FindQueryPostgresTest {

    @Inject
    TestContext testContext;

    @Test
    void selectOne() {
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        postgreQuery.setFields("age","created_at");
        DataNode DataNode = postgreQuery.selectOne(eq("age", 3));
        log.info("第一次打印：{}", JsonUtils.toJsonString(DataNode));
    }


    @Test
    void selectPage() {
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        postgreQuery.setFields("age","created_at");
        DataNodePage DataNodePage = postgreQuery.selectPage(eq("age", 3));
        log.info("第一次打印：{}", JsonUtils.toJsonString(DataNodePage));
    }


    @Test
    void count() {
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        postgreQuery.setFields("age","created_at");
        Integer DataNodePage = postgreQuery.count(eq("age", 3));
        log.info("第一次打印：{}", JsonUtils.toJsonString(DataNodePage));
    }

    @Test
    void selectList(){
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        postgreQuery.setFields("age","created_at","name");
        postgreQuery.getPaging().setSize(1).setNum(2);
        DataNodeList DataNodePage = postgreQuery.selectList(eq("age", 3));
        log.info("第一次打印：{}", JsonUtils.toJsonString(DataNodePage));
    }

    @Test
    void listIndexes(){
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        DataNodeList DataNodePage = postgreQuery.listIndexes();
        log.info("第一次打印：{}", JsonUtils.toJsonString(DataNodePage));
    }

    @Test
    void schemaManage(){
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        var DataNodePage = postgreQuery.createSchema("test");
        postgreQuery.dropSchema("test");
    }

    @Test
    void selectColumns(){
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        var DataNodePage = postgreQuery.selectColumns(testContext.getContextPostgres().getTableNameChain(),false);
        System.out.println(DataNodePage.toPrettyJson());
    }

    @Test
    void selectTables(){
        QueryExecutor postgreQuery = PostgresQueryExecutor.context(testContext.getContextPostgres());
        var DataNodePage = postgreQuery.selectTables("sys");
        System.out.println(DataNodePage);
    }
}
