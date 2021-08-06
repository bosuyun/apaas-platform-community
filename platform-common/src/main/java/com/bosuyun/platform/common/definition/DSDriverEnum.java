package com.bosuyun.platform.common.definition;


import lombok.Getter;

/**
 * Created by liuyuancheng on 2020/12/11  <br/>
 */
public enum DSDriverEnum {

    POSTGRES("org.postgresql.Driver", "org.hibernate.dialect.PostgreSQLDialect"),
    MYSQL5("com.mysql.cj.jdbc.Driver", "org.hibernate.dialect.MySQL5Dialect"),
    MYSQL8("com.mysql.cj.jdbc.Driver", "org.hibernate.dialect.MySQL8Dialect");

    @Getter
    private String driver;

    @Getter
    private String dialect;

    DSDriverEnum(String driverName, String dialect) {
        this.driver = driverName;
        this.dialect = dialect;
    }
}
