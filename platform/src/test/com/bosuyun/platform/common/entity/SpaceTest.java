package com.bosuyun.platform.common.entity;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SpaceTest {

    @Test
    void test(){
        PanacheMock.mock(Space.class);
        Assertions.assertEquals(0, Space.count());
        System.out.println(Space.count());
    }
}