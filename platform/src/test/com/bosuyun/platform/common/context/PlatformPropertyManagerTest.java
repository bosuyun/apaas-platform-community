package com.bosuyun.platform.common.context;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

@QuarkusTest
class PlatformPropertyManagerTest {

    @Test
    @Transactional
    void get() {
        PlatformPropertyManager.put("speed", 111);
        var t = PlatformPropertyManager.asInteger("speed");
        Assertions.assertEquals(111, t);
    }

    @Test
    void put() {

    }

    @Test
    void add() {
    }
}