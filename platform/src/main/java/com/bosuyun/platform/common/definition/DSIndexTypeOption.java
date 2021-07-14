package com.bosuyun.platform.common.definition;

/**
 * Created by liuyuancheng on 2020/12/31  <br/>
 */
public enum DSIndexTypeOption {
    /**
     * 唯一索引
     */
    Unique,
    /**
     * 部分（条件）索引，就是在插入数据的时候，如果数据满足一定的条件，才将该条数据建立索引。
     */
    Partial,
    /**
     * 稀疏索引。由于MongoDB插入的数据是Schemaless的。所以如果某个字段不存在，就不对该条记录建立索引。也就是说，通过索引无法查询到这条插入数据的存在。
     */
    Sparse,
    /**
     * 过期索引。对索引的时间字段添加过期限制。超过一定的时间之后，该条记录就会被删除。
     */
    TTL

}
