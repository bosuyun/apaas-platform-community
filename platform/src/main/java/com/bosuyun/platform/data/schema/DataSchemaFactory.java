package com.bosuyun.platform.data.schema;

import com.bosuyun.platform.common.schema.*;
import com.bosuyun.platform.data.msic.DataSchemaTree;
import lombok.Data;

/**
 * Created by liuyuancheng on 2021/1/28  <br/>
 */
@Data
public class DataSchemaFactory {

    private DataSchemaFactory(){

    }

    public static ArrayType array() {
        return new ArrayType();
    }

    public static StringType string() {
        return new StringType();
    }

    public static NumberType number() {
        return new NumberType();
    }

    public static DateTimeType datetime() {
        return new DateTimeType();
    }

    public static BooleanType bool() {
        return new BooleanType();
    }

    public static ImageType image(){
        return new ImageType();
    }

    public static DataSchemaTree newTree(){
        return new DataSchemaTree().initAsRoot();
    }

    public static SchemaNode integer() {
        return new IntegerType();
    }
}
