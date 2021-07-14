package com.bosuyun.platform.data.schema;

import com.bosuyun.platform.common.schema.StringType;

/**
 * Created by liuyuancheng on 2021/1/28  <br/>
 */
public class DateTimeType extends StringType {

    {
        setFormat("date-time");
    }

}
