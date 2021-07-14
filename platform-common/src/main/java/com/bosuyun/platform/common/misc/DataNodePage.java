package com.bosuyun.platform.common.misc;

import com.bosuyun.platform.data.driver.query.Paging;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 页面
 * <p>
 * Created by liuyuancheng on 2020/8/5  <br/>
 */
@ApiModel(value = "分页")
@Data
@Accessors(chain = true)
public class DataNodePage {

    public DataNodePage(Paging paging) {
        this.pageSize = paging.getSize();
        this.pageNum = paging.getNum();
    }

    @ApiModelProperty(value = "页面条数")
    private int pageSize;

    @ApiModelProperty(value = "页码")
    private int pageNum;

    @ApiModelProperty(value = "总条数")
    private int total;

    @ApiModelProperty(value = "页数")
    private int pages;

    @ApiModelProperty(value = "是否有下页")
    private Boolean hasNext;

    @ApiModelProperty(value = "数据列表")
    private DataNodeList list;
}
