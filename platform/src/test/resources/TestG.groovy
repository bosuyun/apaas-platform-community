package resources;

import com.bosuyun.platform.common.misc.DataNode
import com.bosuyun.platform.common.context.ReqContext

import java.time.LocalDateTime

static def process(ReqContext ctx, DataNode data) {
    ctx.setDataAppId(1L)
    ctx.setSchemaId(1L)
    ctx.setLaunchAt(LocalDateTime.now())
    ctx.setTableName("tableName")
    data.put("age", data.asIntPlus("age", -3))
    data.put("ctx", ctx)
    return data
}

process(ctx, data)
