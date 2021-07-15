package com.bosuyun.platform.common.eventbus;

import com.bosuyun.platform.common.utils.ResourceUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by liuyuancheng on 2021/6/1  <br/>
 */
@QuarkusTest
public class PolyglotTest {

    @Test
    void test() {
        Context polyglot = Context.create();
        Value array = polyglot.eval("js", "[1,2,42,4]");
        int result = array.getArrayElement(2).asInt();
        System.out.println(result);
    }


    @Test
    void perform() throws IOException {
        String script = ResourceUtils.loadFile("perform.mjs");
        Source source = Source.newBuilder("js", script, "script.mjs")
                .mimeType("application/javascript+module")
                .build();
        System.out.println(source);
        Context polyglot = Context.create();
        Value array = polyglot.eval(source);
        int result = array.getArrayElement(2).asInt();
        System.out.println(result);
    }
}
