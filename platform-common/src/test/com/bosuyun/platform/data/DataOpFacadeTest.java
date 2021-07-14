package com.bosuyun.platform.data;

import com.bosuyun.platform.TestContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.utils.JsonUtils;
import com.bosuyun.platform.data.driver.query.Paging;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.bosuyun.platform.data.driver.query.SqlOperatorFactory.emptyClause;
import static com.bosuyun.platform.data.driver.query.SqlOperatorFactory.id;

@QuarkusTest
class DataOpFacadeTest {

    @Inject
    TestContext testContext;

    @Inject
    DataOpFacade queryEngineService;

    @Test
    void insertOne() {
        queryEngineService.initialize(testContext.getContextPostgres());
        DataNode dataNode = new DataNode("age", 11)
                .append("name", "zhangsan")
                .append("address", "南京");
        var returning = queryEngineService.insertOne(dataNode);
        System.out.println(returning);
    }

    @Test
    void insertMany() {
        System.out.println(StringUtils.join(Arrays.asList(1, "33"), ","));
    }

    @Test
    void updateOne() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning = queryEngineService.updateOne(id(42), new DataNode("age", 110)
                .append("name", "lisi"+ LocalDateTime.now()));
        System.out.println(returning);
    }

    @Test
    void updateMany() {
    }

    @Test
    void deleteOne() {
        queryEngineService.initialize(testContext.getContextPostgres());
        queryEngineService.undoDelete(id(41));
        var returning = queryEngineService.deleteOne(id(41));
        System.out.println(returning);
    }

    @Test
    void deleteMany() {

    }

    @Test
    void selectOne() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning = queryEngineService.selectOne(id(42));
        System.out.println(returning);
    }

    @Test
    void selectPage() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning = queryEngineService.selectPage(emptyClause(),
                Paging.of(6,3).sortBy("id", Paging.Direction.ASC).sortBy("created_at", Paging.Direction.DESC)
        );
        System.out.println(JsonUtils.toJsonString(returning));
    }

    @Test
    void selectList() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning = queryEngineService.selectList(emptyClause());
        System.out.println(returning);
    }

    @Test
    void count() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning =  queryEngineService.count(emptyClause());
        System.out.println(returning);
    }

    @Test
    void getIndexes() {
        queryEngineService.initialize(testContext.getContextPostgres());
        var returning =  queryEngineService.getIndexes();
        System.out.println(returning);
    }

    @Test
    void createIndex() {
    }

    @Test
    void dropIndex() {
    }

    @Test
    void undoDelete() {
    }
}