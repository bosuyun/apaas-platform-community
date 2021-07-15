package com.bosuyun.platform.common.eventbus.message.codecs;

import com.bosuyun.platform.common.eventbus.message.DataOpMessage;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.Json;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据操作解码器
 * <p>
 * Created by liuyuancheng on 2021/7/9  <br/>
 */
@Slf4j
public class DataOpMessageCodec implements MessageCodec<DataOpMessage, DataOpMessage> {

    @Override
    public void encodeToWire(Buffer buffer, DataOpMessage dataOpMessage) {
        log.debug("encodeToWire {}",dataOpMessage.toString());
        Buffer encoded = Json.encodeToBuffer(dataOpMessage);
        buffer.appendInt(encoded.length());
        buffer.appendBuffer(encoded);
    }

    @Override
    public DataOpMessage decodeFromWire(int pos, Buffer buffer) {
        log.debug("decodeFromWire");
        int length = buffer.getInt(pos);
        pos += 4;
        return Json.decodeValue(buffer.slice(pos, pos + length), DataOpMessage.class);
    }

    @Override
    public DataOpMessage transform(DataOpMessage dataOpMessage) {
//        dataOpMessage.toString();
        return dataOpMessage;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
