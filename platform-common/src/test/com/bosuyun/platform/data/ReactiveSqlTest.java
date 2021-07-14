package com.bosuyun.platform.data;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.mutiny.pgclient.PgPool;
import org.junit.jupiter.api.Test;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@QuarkusTest
public class ReactiveSqlTest {

    @Test
    void findById(){
        PgPool client = PgPool.pool();
    }

}
