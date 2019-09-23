package com.woniu.orders.entity;

import java.util.ArrayList;
import java.util.List;

public class SeatinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SeatinfoExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRowIsNull() {
            addCriterion("`row` is null");
            return (Criteria) this;
        }

        public Criteria andRowIsNotNull() {
            addCriterion("`row` is not null");
            return (Criteria) this;
        }

        public Criteria andRowEqualTo(String value) {
            addCriterion("`row` =", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotEqualTo(String value) {
            addCriterion("`row` <>", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowGreaterThan(String value) {
            addCriterion("`row` >", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowGreaterThanOrEqualTo(String value) {
            addCriterion("`row` >=", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowLessThan(String value) {
            addCriterion("`row` <", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowLessThanOrEqualTo(String value) {
            addCriterion("`row` <=", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowLike(String value) {
            addCriterion("`row` like", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotLike(String value) {
            addCriterion("`row` not like", value, "row");
            return (Criteria) this;
        }

        public Criteria andRowIn(List<String> values) {
            addCriterion("`row` in", values, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotIn(List<String> values) {
            addCriterion("`row` not in", values, "row");
            return (Criteria) this;
        }

        public Criteria andRowBetween(String value1, String value2) {
            addCriterion("`row` between", value1, value2, "row");
            return (Criteria) this;
        }

        public Criteria andRowNotBetween(String value1, String value2) {
            addCriterion("`row` not between", value1, value2, "row");
            return (Criteria) this;
        }

        public Criteria andColumnIsNull() {
            addCriterion("`column` is null");
            return (Criteria) this;
        }

        public Criteria andColumnIsNotNull() {
            addCriterion("`column` is not null");
            return (Criteria) this;
        }

        public Criteria andColumnEqualTo(String value) {
            addCriterion("`column` =", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnNotEqualTo(String value) {
            addCriterion("`column` <>", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnGreaterThan(String value) {
            addCriterion("`column` >", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnGreaterThanOrEqualTo(String value) {
            addCriterion("`column` >=", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnLessThan(String value) {
            addCriterion("`column` <", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnLessThanOrEqualTo(String value) {
            addCriterion("`column` <=", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnLike(String value) {
            addCriterion("`column` like", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnNotLike(String value) {
            addCriterion("`column` not like", value, "column");
            return (Criteria) this;
        }

        public Criteria andColumnIn(List<String> values) {
            addCriterion("`column` in", values, "column");
            return (Criteria) this;
        }

        public Criteria andColumnNotIn(List<String> values) {
            addCriterion("`column` not in", values, "column");
            return (Criteria) this;
        }

        public Criteria andColumnBetween(String value1, String value2) {
            addCriterion("`column` between", value1, value2, "column");
            return (Criteria) this;
        }

        public Criteria andColumnNotBetween(String value1, String value2) {
            addCriterion("`column` not between", value1, value2, "column");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`state` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`state` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andMsidIsNull() {
            addCriterion("msid is null");
            return (Criteria) this;
        }

        public Criteria andMsidIsNotNull() {
            addCriterion("msid is not null");
            return (Criteria) this;
        }

        public Criteria andMsidEqualTo(Integer value) {
            addCriterion("msid =", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotEqualTo(Integer value) {
            addCriterion("msid <>", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidGreaterThan(Integer value) {
            addCriterion("msid >", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("msid >=", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidLessThan(Integer value) {
            addCriterion("msid <", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidLessThanOrEqualTo(Integer value) {
            addCriterion("msid <=", value, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidIn(List<Integer> values) {
            addCriterion("msid in", values, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotIn(List<Integer> values) {
            addCriterion("msid not in", values, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidBetween(Integer value1, Integer value2) {
            addCriterion("msid between", value1, value2, "msid");
            return (Criteria) this;
        }

        public Criteria andMsidNotBetween(Integer value1, Integer value2) {
            addCriterion("msid not between", value1, value2, "msid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}