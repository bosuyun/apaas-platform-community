package com.bosuyun.platform.common;

import com.google.common.base.CaseFormat;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 按照数据库命名规范，snake case
 *
 * Created by liuyuancheng on 2021/3/3  <br/>
 */
public class ModifiedImprovedNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public final Identifier toPhysicalColumnName(final Identifier name, final JdbcEnvironment context) {
        return new Identifier(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name.getText()), name.isQuoted());
    }

    @Override
    public final Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name.getText())
                .replace("__","_"), name.isQuoted());
    }

}
