package com.bosuyun.platform.common.misc;



import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 页面
 * <p>
 * Created by liuyuancheng on 2020/8/5  <br/>
 */
//分页")
@Data
@Accessors(chain = true)
public class DataNodePage {

    public DataNodePage(Paging paging) {
        this.pageSize = paging.getSize();
        this.pageNum = paging.getNum();
    }

    //页面条数")
    private int pageSize;

    //页码")
    private int pageNum;

    //总条数")
    private int total;

    //页数")
    private int pages;

    //是否有下页")
    private Boolean hasNext;

    //数据列表")
    private DataNodeList list;
}
