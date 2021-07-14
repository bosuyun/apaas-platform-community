package com.bosuyun.platform.common.definition;

import java.util.Arrays;

/**
 * Created by liuyuancheng on 2020/12/31  <br/>
 */
public enum DSIndexType {
    /**
     * 哈希索引。单值匹配快，不支持范围查询。
     */
    hashed,
    /**
     * 也是需要先分词再建立“倒排索引”，可以看做是一个玩具版的ES。
     */
    text,
    /**
     * 经纬度索引。其目的是方便经纬度相关的查找操作。
     */
    geospatial,
    /**
     * 多键索引。就是对数组中的每一个元素建立索引。
     */
    multikey;

    public static boolean isMultiField(DSIndexType indexType){
        return Arrays.asList(multikey).contains(indexType);
    }

}
