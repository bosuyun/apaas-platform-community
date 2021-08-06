package com.bosuyun.platform.data;


import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


class DatasourceControllerTest {

    @Test
    void list() {
        var ret = given()
                .queryParams(new HashMap<String, String>() {{
                    put("driver", "MONGODB");
                }})
                .when()
                .get("/api/datasource/list")
                .andReturn()
                .body()
                .asString();
        System.out.println(ret);
    }

    @Test
    void findOne() {
        var ret = given().get("/api/datasource/2").andReturn().body().asString();
        System.out.println(ret);
    }

}