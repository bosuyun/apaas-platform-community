package com.bosuyun.platform.common.misc;

import com.bosuyun.platform.common.utils.JsonUtils;

import java.util.LinkedList;
import java.util.List;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * 数据返回、查询条件等datasource层转换中枢
 * <p>
 * Created by liuyuancheng on 2021/3/23  <br/>
 */
public class DataNodeList extends LinkedList<DataNode> {


    public DataNodeList() {
        super();
    }

    public DataNodeList(List<?> inputs){
        for (Object object : inputs) {
            this.add(new DataNode(object));
        }
    }

    public DataNodeList(DataNode dataNode) {
        this.add(dataNode);
    }

    public boolean nonEmpty() {
        return !isEmpty();
    }

    public <T> List<T> extractFieldList(String fieldName, Class<T> clazz){
        var returning  = new LinkedList<T>();
        for (DataNode dataNode : this) {
            dataNode.get(fieldName);
        }
        return returning;
    }

    @Override
    public String toString(){
        return JsonUtils.toJsonString(this);
    }


}
