package com.bosuyun.platform.common.utils;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Created by liuyuancheng on 2020/12/18  <br/>
 */
@QuarkusTest
@Slf4j
public class AesUtilsTest {
    @Test
    void encrypt() {
        log.info("jerrylau->{}", AesUtils.encrypt("jerrylau"));
        log.info("legoland->{}", AesUtils.encrypt("legoland"));
        log.info("maolyc000->{}", AesUtils.encrypt("maolyc000"));
    }
}
