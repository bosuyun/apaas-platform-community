package com.bosuyun.platform.common.definition;

/**
 * Created by liuyuancheng on 2021/1/21  <br/>
 */
public enum DataFieldWriteMode {
    ALL_WRITE(0),
    UI_WRITE_ONLY(1),
    API_WRITE_ONLY(2),
    ;
    private final Integer code;

    public Integer getCode() {
        return code;
    }

    DataFieldWriteMode(int i) {
        this.code = i;
    }
}
