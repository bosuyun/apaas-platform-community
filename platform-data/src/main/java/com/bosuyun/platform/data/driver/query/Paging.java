package com.bosuyun.platform.data.driver.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 分页参数
 * <p>
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Paging {

    public enum Direction {
        DESC, ASC
    }

    @Data
    public static class Sort {
        private String field;
        private Direction direction;

        public Sort(String field1, Direction direction1) {
            this.direction = direction1;
            this.field = field1;
        }
    }

    /**
     * 页码
     */
    private int num = 1;

    /**
     * 每页条数
     */
    private int size = 10;

    private List<Sort> sorts = new LinkedList<>();

    public Paging(int pageNum, int pageSize) {
        this.num = pageNum;
        this.size = pageSize;
    }

    /**
     * 添加排序
     *
     * @param field
     * @param direction
     * @return
     */
    public Paging sortBy(String field, Direction direction) {
        this.sorts.add(new Sort(field, direction));
        return this;
    }

    public Map<String, Direction> getOrderByMap() {
        Map<String, Direction> returning = new LinkedHashMap<>();
        if (!this.sorts.isEmpty()) {
            for (Sort sort : this.sorts) {
                returning.put(sort.getField(), sort.getDirection());
            }
        }
        return returning;
    }

    public static Paging of(int pageNum, int pageSize) {
        return new Paging(pageNum, pageSize);
    }

}
