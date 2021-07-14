package com.bosuyun.platform.data;

import com.bosuyun.platform.common.definition.DSDriverTypeEnum;
import com.bosuyun.platform.data.msic.DatasourceModel;
import com.bosuyun.platform.common.utils.BeanUtils;
import com.bosuyun.platform.common.utils.PanacheUtils;
import com.bosuyun.platform.common.entity.Datasource;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/3/10  <br/>
 */
@ApplicationScoped
@Slf4j
public class DatasourceService {

    /**
     * 获取数据源
     *
     * @param name
     * @param host
     * @param driver
     * @return
     */
    public List<Datasource> list(String name, String host, String driver) {
        return this.list(name, host, driver, 0, 10);
    }

    /**
     * 查询数据源列表
     *
     * @param name
     * @param host
     * @param driver
     * @return
     */
    public List<Datasource> list(String name, String host, String driver, Integer pageNum, Integer pageSize) {
        var queryParams = new HashMap<String, Object>() {{
            put("name", name);
            put("host", host);
            try {
                put("driver", DSDriverTypeEnum.valueOf(driver));
            } catch (IllegalArgumentException e) {
                put("driver", null);
            }
        }};
        var queryString = PanacheUtils.queryConditionJoining(queryParams);
        log.debug("list queryString: {}, {}", queryString, PanacheUtils.removeInvalidParams(queryParams));
        PanacheQuery<Datasource> panacheQuery;
        if (StringUtils.isEmpty(queryString)) {
            panacheQuery = Datasource.find("");
        } else {
            panacheQuery = Datasource.find(PanacheUtils.queryConditionJoining(queryParams), PanacheUtils.removeInvalidParams(queryParams));
        }
        return panacheQuery.page(pageNum, pageSize).list();
    }

    public Datasource findById(Long id) {
        return Datasource.findById(id);
    }

    /**
     * 持久化
     *
     * @param datasourceModel
     * @return
     */
    @Transactional
    public Datasource persist(DatasourceModel datasourceModel) {
        Datasource datasource = new Datasource();
        if (Objects.isNull(datasourceModel.getId())) {
            log.debug("创建新条目 {}", datasourceModel);
            datasource = BeanUtils.copyNonNullProperties(datasourceModel, datasource);
        } else {
            datasource = findById(datasourceModel.getId());
            datasource = BeanUtils.copyNonNullProperties(datasourceModel, datasource);
        }
        return datasource;
    }
}
