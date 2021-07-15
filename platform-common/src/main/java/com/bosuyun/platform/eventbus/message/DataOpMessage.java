package com.bosuyun.platform.eventbus.message;

import com.bosuyun.platform.common.exception.BizzException;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodeList;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * 数据操作
 * <p>
 * Created by liuyuancheng on 2021/6/19  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataOpMessage extends EventMessage {

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

    {
        setMessageType(this.getClass().getSimpleName());
    }

    public DataOpMessage update() {
        this.setAction(DATA_OP_UPDATE);
        return this;
    }

    public DataOpMessage insert() {
        this.action  = DATA_OP_INSERT;
        return this;
    }


    public DataOpMessage insertMany() {
        this.action =DATA_OP_INSERT_MANY;
        return this;
    }

    public DataOpMessage undoDelete() {
        this.action =DATA_OP_UNDO_DELETE;
        return this;
    }

    public DataOpMessage delete() {
        this.action = DATA_OP_DELETE;
        return this;
    }

    public DataOpMessage deleteMany() {
        this.action = DATA_OP_DELETE_MANY;
        return this;
    }

    public DataOpMessage query() {
        this.action = DATA_OP_QUERY;
        return this;
    }

    public DataOpMessage setInputData(DataNodeList data) {
        this.inputData = data;
        return this;
    }

    public DataOpMessage setInputData(DataNode data) {
        this.inputData = new DataNodeList(data);
        return this;
    }

    public DataOpMessage setFormerData(DataNodeList data) {
        this.formerData = data;
        return this;
    }

    public DataOpMessage setFormerData(DataNode data) {
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
    public DataOpMessage setUpdateFields(List<String> updateFields) {
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
