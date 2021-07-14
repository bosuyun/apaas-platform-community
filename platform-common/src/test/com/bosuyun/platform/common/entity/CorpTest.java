package com.bosuyun.platform.common.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@QuarkusTest
class CorpTest {

    @Test
    @Transactional
    void create() {
        Corp corp = new Corp().setName("hello" + LocalDateTime.now());
        Corp.persist(corp);
        Space space = new Space().setName("space" + LocalDateTime.now()).setCorp(corp);
        Space.persist(space);
    }

    @Test
    void findCorp() {
        Corp corp = Corp.findById(2L);
        System.out.println(corp.getSpaces());
    }

    @Test
    void findSpace() {
        Space space = Space.findById(1L);
        System.out.println(space);
    }
}