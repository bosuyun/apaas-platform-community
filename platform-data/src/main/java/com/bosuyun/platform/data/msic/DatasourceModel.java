package com.bosuyun.platform.data.msic;

import com.bosuyun.platform.common.definition.DSDriverEnum;


import lombok.Builder;
import lombok.Data;

/**
 * Created by liuyuancheng on 2021/3/11  <br/>
 */
//数据源修改")
@Data
@Builder
public class DatasourceModel {

    //ID")
    private Long id;

    //数据源名称")
    private String name;

    //数据库驱动")
    private DSDriverEnum driver;

    //主机名")
    private String host;

    //端口号")
    private Integer port;

    //用户名")
    private String user;

    //密码")
    private String password;

    //数据库名称：自动加上前缀 data_engine_")
    private String dbname;

    //当前状态是否可用")
    private Boolean available;

    //连接池")
    private Integer poolSize;
}
