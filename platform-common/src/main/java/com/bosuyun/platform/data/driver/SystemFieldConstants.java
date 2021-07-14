package com.bosuyun.platform.data.driver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liuyuancheng on 2021/6/6  <br/>
 */
public class SystemFieldConstants {

    public static final String DB_SYSTEM_FIELD_ID = "id";

    public static final String DB_SYSTEM_FIELD_CREATED_AT = "created_at";
    public static final String DB_SYSTEM_FIELD_UPDATED_AT = "updated_at";
    public static final String DB_SYSTEM_FIELD_DELETED_AT = "deleted_at";
    public static final String DB_SYSTEM_FIELD_DELETED = "deleted";
    public static final String DB_SYSTEM_FIELD_DISCARDED = "discarded";
    public static final String DB_SYSTEM_FIELD_FOR_TEST = "for_test";
    public static final String DB_SYSTEM_FIELD_SID = "sid";


    public static List<String> getAll(){
        return Arrays.asList(DB_SYSTEM_FIELD_ID,DB_SYSTEM_FIELD_CREATED_AT,DB_SYSTEM_FIELD_DELETED,
                DB_SYSTEM_FIELD_UPDATED_AT,DB_SYSTEM_FIELD_DELETED_AT,DB_SYSTEM_FIELD_DISCARDED,DB_SYSTEM_FIELD_FOR_TEST,
                DB_SYSTEM_FIELD_SID
        );
    }

}
