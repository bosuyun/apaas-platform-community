package com.bosuyun.platform.common.misc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序参数
 *
 * Created by liuyuancheng on 2020/12/25  <br/>
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class Sorting {

    /**
     * 顺序排列的字段
     */
    private final List<String> ascFields = new ArrayList<>();

    /**
     * 逆序排列的字段
     */
    private final List<String> descFields = new ArrayList<>();

}
