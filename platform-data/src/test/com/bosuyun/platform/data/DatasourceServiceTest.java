package com.bosuyun.platform.data;

import com.bosuyun.platform.common.definition.DSDriverEnum;
import com.bosuyun.platform.common.entity.Datasource;
import com.bosuyun.platform.data.msic.DatasourceModel;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
class DatasourceServiceTest {

    @Inject
    DatasourceService datasourceFacade;

    @Test
    void findById(){
        System.out.println(Datasource.findByIdFromCache(2L));
    }

    @Test
    void list(){
        System.out.println(datasourceFacade.list("", "", "MONGODB"));
    }

    @Test
    void persist() {
        var t = datasourceFacade.persist(DatasourceModel.builder()
                .dbname("test")
                .driver(DSDriverEnum.POSTGRES)
                .available(true)
                .poolSize(11)
                .password("adsfasdflj")
                .build()
        );
        System.out.println(t);
    }
}
