package com.bosuyun.platform.process.msic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liuyuancheng on 2021/6/29  <br/>
 */
@Data
public class ProcessEngineState {

    private String engineName;

    private State state;

    @Data
    @AllArgsConstructor
    public static class State {

        private Long deployed;

        private Long running;
    }

    public void setState(String engineName,State state){
        this.setEngineName(engineName);
        this.setState(state);
    }
}
