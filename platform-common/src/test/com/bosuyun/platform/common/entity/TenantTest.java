package com.bosuyun.platform.common.entity;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class TenantTest {

    @Test
    void test(){
        PanacheMock.mock(Tenant.class);
        Assertions.assertEquals(0, Tenant.count());
        System.out.println(Tenant.count());
    }
}