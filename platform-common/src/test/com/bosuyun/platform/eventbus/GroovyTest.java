package com.bosuyun.platform.eventbus;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.utils.ResourceUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

/**
 * Created by liuyuancheng on 2021/6/2  <br/>
 */
@QuarkusTest
public class GroovyTest {

    /**
     * todo groovy 方案
     * https://blog.csdn.net/Johnnyz1234/article/details/95773912
     */
    @Test
    void test() {
        String script = ResourceUtils.loadFile("TestG.groovy");
        Binding binding = new Binding();
        binding.setVariable("data", new DataNode("age", 11).append("name", "Yates"));
        binding.setVariable("ctx", new ReqContext());
        GroovyShell shell = new GroovyShell(binding);
        DataNode value = (DataNode) shell.evaluate(script);
        System.out.println(value.toJson());
        shell.getClassLoader().clearCache();
    }

}
