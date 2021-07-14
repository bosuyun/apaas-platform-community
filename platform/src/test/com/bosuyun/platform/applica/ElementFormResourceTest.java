package com.bosuyun.platform.applica;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * 使用文档
 *
 * https://github.com/rest-assured/rest-assured/wiki/Usage
 */
@QuarkusTest
class ElementFormResourceTest {

    @Test
    void form() {
        var ret = given()
                .contentType(ContentType.JSON)
                .formParams(new HashMap<String,Object>(){{
                    put("name","zhangsan");
                }})
                .when().post("/api/dataApp/form").andReturn().body().asString()
        ;
        System.out.println(ret);
    }
}