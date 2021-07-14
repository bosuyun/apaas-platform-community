package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.data.msic.DataDriverException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * where子句
 * <p>
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
public class WhereClause extends SqlClause implements Cloneable {

    public enum Logical {
        OR, AND
    }

    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @Data
    @NoArgsConstructor
    static class ClauseItem extends SqlClause {

        public ClauseItem(String name) {
            this.setField("" + name + "");
        }

        private String field;

        private String value;

        private Boolean not;

        public static ClauseItem newItem(String field) {
            return new ClauseItem(field);
        }

        @Override
        public String toSqlSegment() {
            sqlSegment.setLength(0);
            sqlSegment.append(field).append(value);
            return sqlSegment.toString();
        }
    }

    @Getter
    private Logical logical = Logical.AND;

    @Getter
    private Boolean not = false;

    private final List<String> likeMatchOperator = Arrays.asList("_", "%");

    @Getter
    private List<ClauseItem> clauseItems = new ArrayList<>();

    private List<WhereClause> clauses = new ArrayList<>();

    private WhereClause addClauseItem(ClauseItem item) {
        this.clauseItems.add(item);
        return this;
    }

    public WhereClause setLogicalAnd() {
        this.logical = Logical.AND;
        return this;
    }

    public WhereClause setLogicalOr() {
        this.logical = Logical.OR;
        return this;
    }

    public WhereClause setNot() {
        this.not = true;
        return this;
    }

    public WhereClause addClause(WhereClause clause) {
        this.clauses.add(clause.setLogicalAnd());
        return this;
    }

    public WhereClause addOrClause(WhereClause clause) {
        this.clauses.add(clause.setLogicalOr());
        return this;
    }

    private String valueQuote(Object value) {
        if (value instanceof Integer || value instanceof Double || value instanceof Long) {
            return String.format("%s", value);
        } else {
            return String.format("'%s'", value);
        }
    }

    private WhereClause buildItem(String field, String value) {
        this.addClauseItem(ClauseItem.newItem(field).setValue(value));
        return this;
    }

    /**
     * like语句
     *
     * @param field
     * @param value
     * @param leftMatch  左侧匹配符
     * @param rightMatch 右侧匹配符
     * @return
     */
    public WhereClause like(String field, String value, String leftMatch, String rightMatch) {
        if (!likeMatchOperator.contains(leftMatch) || !likeMatchOperator.contains(rightMatch)) {
            throw new DataDriverException("不支持的通配符");
        }
        return buildItem(field, String.format("LIKE '%s%s%s'", leftMatch, value, rightMatch));
    }

    /**
     * 区间
     *
     * @param field
     * @param leftValue
     * @param rightValue
     * @return
     */
    public WhereClause between(String field, Object leftValue, Object rightValue) {
        if (!leftValue.getClass().getSimpleName().equals(rightValue.getClass().getSimpleName())) {
            throw new DataDriverException("左右边界值的数据类型必须一致");
        }
        return buildItem(field, String.format(" BETWEEN %s AND %s", valueQuote(leftValue), valueQuote(rightValue)));
    }

    public WhereClause in(String field, List<Object> values) {
        return buildItem(field, String.format(" IN (%s) ", values.stream()
                .map(this::valueQuote)
                .collect(Collectors.joining(","))));
    }

    public WhereClause nin(String field, List<Object> values) {
        return buildItem(field, String.format(" NOT IN (%s) ", values.stream()
                .map(this::valueQuote)
                .collect(Collectors.joining(","))));
    }

    public WhereClause eq(String field, Object value) {
        return buildItem(field, " = " + valueQuote(value));
    }

    public WhereClause ne(String field, Object value) {
        return buildItem(field, " <> " + valueQuote(value));
    }

    public WhereClause gte(String field, Object value) {
        return buildItem(field, " >= " + valueQuote(value));
    }

    public WhereClause lte(String field, Object value) {
        return buildItem(field, " <= " + valueQuote(value));
    }

    public WhereClause gt(String field, Object value) {
        return buildItem(field, " > " + valueQuote(value));
    }

    public WhereClause lt(String field, Object value) {
        return buildItem(field, " < " + valueQuote(value));
    }

    /**
     * todo id支持自增和雪花算法，通过配置指定
     *
     * @param value
     * @return
     */
    public WhereClause id(Long value) {
        return buildItem("id", " = " + valueQuote(value));
    }

    @Override
    public String toSqlSegment() {
        this.clauses.add(this);
        sqlSegment = new StringBuffer();
        // clauses和clauseItems均为空
        if (clauses.isEmpty() && clauseItems.isEmpty()) {
            return "";
        } else if (clauses.isEmpty()) {
            // clauses为空
            var Iter = this.getClauseItems().iterator();
            while (Iter.hasNext()) {
                var clauseItem = Iter.next();
                sqlSegment.append(clauseItem.toSqlSegment());
                if (Iter.hasNext()) {
                    sqlSegment.append(" ").append(this.getLogical().toString()).append(" ");
                }
            }
            return sqlSegment.toString();
        } else {
            // clauses和clauseItems不为空
            var clausesIter = clauses.iterator();
            if (this.getNot()) {
                sqlSegment.append("NOT ");
            }
            sqlSegment.append("(");
            while (clausesIter.hasNext()) {
                var clause = clausesIter.next();
                if (clause.getNot()) {
                    sqlSegment.append("NOT ");
                }
                if (clauses.size() >= 2) {
                    sqlSegment.append("(");
                }
                // clauseItems Begin
                if (clauseItems.size() >= 2) {
                    sqlSegment.append("(");
                }
                var Iter = clause.getClauseItems().iterator();
                while (Iter.hasNext()) {
                    var clauseItem = Iter.next();
                    sqlSegment.append(clauseItem.toSqlSegment());
                    if (Iter.hasNext()) {
                        sqlSegment.append(" ").append(clause.getLogical().toString()).append(" ");
                    }
                }
                if (clauseItems.size() >= 2) {
                    sqlSegment.append(")");
                }
                if (clauses.size() >= 2) {
                    sqlSegment.append(")");
                }
                // clauseItems End
                if (clausesIter.hasNext()) {
                    sqlSegment.append(" ").append(this.logical.toString()).append(" ");
                }
            }
            return sqlSegment.append(")").toString();
        }
    }
}
