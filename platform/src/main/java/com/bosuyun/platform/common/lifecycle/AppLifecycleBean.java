package com.bosuyun.platform.common.lifecycle;

import com.bosuyun.platform.common.entity.Member;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

/**
 * Created by liuyuancheng on 2021/6/20  <br/>
 */
@ApplicationScoped
@Slf4j
public class AppLifecycleBean {

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        // 检查是否有系统用户，没有则创建
        if (Member.findSystemMember().isEmpty()) {
            Member member = new Member();
            member.setJoinMethod(0);
            member.setRealName("系统");
            member.setUsername("system");
            Member.persist(member);
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("The application is stopping...");
    }

}
