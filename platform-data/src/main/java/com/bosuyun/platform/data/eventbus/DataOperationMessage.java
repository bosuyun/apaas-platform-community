package com.bosuyun.platform.data.eventbus;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.exception.BizzException;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodeList;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * 数据操作
 * <p>
 * Created by liuyuancheng on 2021/6/19  <br/>
 */
@Data
public class DataOperationMessage {

    /**
     * 操作
     */
    private String action;

    /**
     * 数据数据
     */
    private DataNodeList inputData;
    /**
     * update前的老数据
     */
    private DataNodeList formerData;
    /**
     * 要更新的字段
     */
    private List<String> updateFields;

    private final static String DATA_OP_UPDATE = "update";
    private final static String DATA_OP_QUERY = "query";
    private final static String DATA_OP_INSERT = "insert";
    private final static String DATA_OP_INSERT_MANY = "insertMany";
    private final static String DATA_OP_DELETE = "delete";
    private final static String DATA_OP_UNDO_DELETE = "undoDelete";
    private final static String DATA_OP_DELETE_MANY = "deleteMany";

    /**
     * 动作
     */
    @Setter
    @Getter
    private String messageType;

    private ReqContext reqContext;

    public Boolean hasReqContext(){
        return Objects.nonNull(reqContext);
    }

    public DataOperationMessage setReqContext(ReqContext reqContext) {
        this.reqContext = reqContext;
        return this;
    }

    public ReqContext getReqContext() {
        return reqContext;
    }


    public DataOperationMessage update() {
        this.setAction(DATA_OP_UPDATE);
        return this;
    }

    public DataOperationMessage insert() {
        this.action  = DATA_OP_INSERT;
        return this;
    }


    public DataOperationMessage insertMany() {
        this.action =DATA_OP_INSERT_MANY;
        return this;
    }

    public DataOperationMessage undoDelete() {
        this.action =DATA_OP_UNDO_DELETE;
        return this;
    }

    public DataOperationMessage delete() {
        this.action = DATA_OP_DELETE;
        return this;
    }

    public DataOperationMessage deleteMany() {
        this.action = DATA_OP_DELETE_MANY;
        return this;
    }

    public DataOperationMessage query() {
        this.action = DATA_OP_QUERY;
        return this;
    }

    public DataOperationMessage setInputData(DataNodeList data) {
        this.inputData = data;
        return this;
    }

    public DataOperationMessage setInputData(DataNode data) {
        this.inputData = new DataNodeList(data);
        return this;
    }

    public DataOperationMessage setFormerData(DataNodeList data) {
        this.formerData = data;
        return this;
    }

    public DataOperationMessage setFormerData(DataNode data) {
        if (!this.action.equals(DATA_OP_UPDATE)) {
            throw new BizzException("updateFields only support for update action.");
        }
        if (this.inputData.size() > 1) {
            throw new BizzException("Only support update 1 item, or dose not set oldData.");
        }
        this.formerData = new DataNodeList(data);
        return this;
    }

    /**
     * 被更新的字段
     *
     * @param updateFields
     * @return
     */
    public DataOperationMessage setUpdateFields(List<String> updateFields) {
        if (!this.action.equals(DATA_OP_UPDATE)) {
            throw new BizzException("updateFields only support for update action.");
        }
        this.updateFields = updateFields;
        return this;
    }

    @Override
    public String toString() {
        return toJsonString(this);
    }
}
