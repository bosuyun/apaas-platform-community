package com.bosuyun.platform.data.msic;

import com.bosuyun.platform.common.definition.DSDriverTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by liuyuancheng on 2021/3/11  <br/>
 */
@ApiModel(description = "数据源修改")
@Data
@Builder
public class DatasourceModel {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "数据源名称")
    private String name;

    @ApiModelProperty(value = "数据库驱动")
    private DSDriverTypeEnum driver;

    @ApiModelProperty(value = "主机名")
    private String host;

    @ApiModelProperty(value = "端口号")
    private Integer port;

    @ApiModelProperty(value = "用户名")
    private String user;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "数据库名称：自动加上前缀 data_engine_")
    private String dbname;

    @ApiModelProperty(value = "当前状态是否可用")
    private Boolean available;

    @ApiModelProperty(value = "连接池")
    private Integer poolSize;
}
