package com.woniu.orders.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andMnameIsNull() {
            addCriterion("mName is null");
            return (Criteria) this;
        }

        public Criteria andMnameIsNotNull() {
            addCriterion("mName is not null");
            return (Criteria) this;
        }

        public Criteria andMnameEqualTo(String value) {
            addCriterion("mName =", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotEqualTo(String value) {
            addCriterion("mName <>", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThan(String value) {
            addCriterion("mName >", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameGreaterThanOrEqualTo(String value) {
            addCriterion("mName >=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThan(String value) {
            addCriterion("mName <", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLessThanOrEqualTo(String value) {
            addCriterion("mName <=", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameLike(String value) {
            addCriterion("mName like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotLike(String value) {
            addCriterion("mName not like", value, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameIn(List<String> values) {
            addCriterion("mName in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotIn(List<String> values) {
            addCriterion("mName not in", values, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameBetween(String value1, String value2) {
            addCriterion("mName between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andMnameNotBetween(String value1, String value2) {
            addCriterion("mName not between", value1, value2, "mname");
            return (Criteria) this;
        }

        public Criteria andTicketsIsNull() {
            addCriterion("tickets is null");
            return (Criteria) this;
        }

        public Criteria andTicketsIsNotNull() {
            addCriterion("tickets is not null");
            return (Criteria) this;
        }

        public Criteria andTicketsEqualTo(Double value) {
            addCriterion("tickets =", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsNotEqualTo(Double value) {
            addCriterion("tickets <>", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsGreaterThan(Double value) {
            addCriterion("tickets >", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsGreaterThanOrEqualTo(Double value) {
            addCriterion("tickets >=", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsLessThan(Double value) {
            addCriterion("tickets <", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsLessThanOrEqualTo(Double value) {
            addCriterion("tickets <=", value, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsIn(List<Double> values) {
            addCriterion("tickets in", values, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsNotIn(List<Double> values) {
            addCriterion("tickets not in", values, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsBetween(Double value1, Double value2) {
            addCriterion("tickets between", value1, value2, "tickets");
            return (Criteria) this;
        }

        public Criteria andTicketsNotBetween(Double value1, Double value2) {
            addCriterion("tickets not between", value1, value2, "tickets");
            return (Criteria) this;
        }

        public Criteria andComntryIsNull() {
            addCriterion("comntry is null");
            return (Criteria) this;
        }

        public Criteria andComntryIsNotNull() {
            addCriterion("comntry is not null");
            return (Criteria) this;
        }

        public Criteria andComntryEqualTo(String value) {
            addCriterion("comntry =", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryNotEqualTo(String value) {
            addCriterion("comntry <>", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryGreaterThan(String value) {
            addCriterion("comntry >", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryGreaterThanOrEqualTo(String value) {
            addCriterion("comntry >=", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryLessThan(String value) {
            addCriterion("comntry <", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryLessThanOrEqualTo(String value) {
            addCriterion("comntry <=", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryLike(String value) {
            addCriterion("comntry like", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryNotLike(String value) {
            addCriterion("comntry not like", value, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryIn(List<String> values) {
            addCriterion("comntry in", values, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryNotIn(List<String> values) {
            addCriterion("comntry not in", values, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryBetween(String value1, String value2) {
            addCriterion("comntry between", value1, value2, "comntry");
            return (Criteria) this;
        }

        public Criteria andComntryNotBetween(String value1, String value2) {
            addCriterion("comntry not between", value1, value2, "comntry");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNull() {
            addCriterion("uptime is null");
            return (Criteria) this;
        }

        public Criteria andUptimeIsNotNull() {
            addCriterion("uptime is not null");
            return (Criteria) this;
        }

        public Criteria andUptimeEqualTo(Date value) {
            addCriterion("uptime =", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotEqualTo(Date value) {
            addCriterion("uptime <>", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThan(Date value) {
            addCriterion("uptime >", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uptime >=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThan(Date value) {
            addCriterion("uptime <", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeLessThanOrEqualTo(Date value) {
            addCriterion("uptime <=", value, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeIn(List<Date> values) {
            addCriterion("uptime in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotIn(List<Date> values) {
            addCriterion("uptime not in", values, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeBetween(Date value1, Date value2) {
            addCriterion("uptime between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andUptimeNotBetween(Date value1, Date value2) {
            addCriterion("uptime not between", value1, value2, "uptime");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("score like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("score not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andShortStoryIsNull() {
            addCriterion("short_story is null");
            return (Criteria) this;
        }

        public Criteria andShortStoryIsNotNull() {
            addCriterion("short_story is not null");
            return (Criteria) this;
        }

        public Criteria andShortStoryEqualTo(String value) {
            addCriterion("short_story =", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryNotEqualTo(String value) {
            addCriterion("short_story <>", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryGreaterThan(String value) {
            addCriterion("short_story >", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryGreaterThanOrEqualTo(String value) {
            addCriterion("short_story >=", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryLessThan(String value) {
            addCriterion("short_story <", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryLessThanOrEqualTo(String value) {
            addCriterion("short_story <=", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryLike(String value) {
            addCriterion("short_story like", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryNotLike(String value) {
            addCriterion("short_story not like", value, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryIn(List<String> values) {
            addCriterion("short_story in", values, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryNotIn(List<String> values) {
            addCriterion("short_story not in", values, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryBetween(String value1, String value2) {
            addCriterion("short_story between", value1, value2, "shortStory");
            return (Criteria) this;
        }

        public Criteria andShortStoryNotBetween(String value1, String value2) {
            addCriterion("short_story not between", value1, value2, "shortStory");
            return (Criteria) this;
        }

        public Criteria andPreMovieIsNull() {
            addCriterion("pre_movie is null");
            return (Criteria) this;
        }

        public Criteria andPreMovieIsNotNull() {
            addCriterion("pre_movie is not null");
            return (Criteria) this;
        }

        public Criteria andPreMovieEqualTo(String value) {
            addCriterion("pre_movie =", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieNotEqualTo(String value) {
            addCriterion("pre_movie <>", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieGreaterThan(String value) {
            addCriterion("pre_movie >", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieGreaterThanOrEqualTo(String value) {
            addCriterion("pre_movie >=", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieLessThan(String value) {
            addCriterion("pre_movie <", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieLessThanOrEqualTo(String value) {
            addCriterion("pre_movie <=", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieLike(String value) {
            addCriterion("pre_movie like", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieNotLike(String value) {
            addCriterion("pre_movie not like", value, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieIn(List<String> values) {
            addCriterion("pre_movie in", values, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieNotIn(List<String> values) {
            addCriterion("pre_movie not in", values, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieBetween(String value1, String value2) {
            addCriterion("pre_movie between", value1, value2, "preMovie");
            return (Criteria) this;
        }

        public Criteria andPreMovieNotBetween(String value1, String value2) {
            addCriterion("pre_movie not between", value1, value2, "preMovie");
            return (Criteria) this;
        }

        public Criteria andLongtimeIsNull() {
            addCriterion("longtime is null");
            return (Criteria) this;
        }

        public Criteria andLongtimeIsNotNull() {
            addCriterion("longtime is not null");
            return (Criteria) this;
        }

        public Criteria andLongtimeEqualTo(String value) {
            addCriterion("longtime =", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotEqualTo(String value) {
            addCriterion("longtime <>", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThan(String value) {
            addCriterion("longtime >", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeGreaterThanOrEqualTo(String value) {
            addCriterion("longtime >=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThan(String value) {
            addCriterion("longtime <", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLessThanOrEqualTo(String value) {
            addCriterion("longtime <=", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeLike(String value) {
            addCriterion("longtime like", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotLike(String value) {
            addCriterion("longtime not like", value, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeIn(List<String> values) {
            addCriterion("longtime in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotIn(List<String> values) {
            addCriterion("longtime not in", values, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeBetween(String value1, String value2) {
            addCriterion("longtime between", value1, value2, "longtime");
            return (Criteria) this;
        }

        public Criteria andLongtimeNotBetween(String value1, String value2) {
            addCriterion("longtime not between", value1, value2, "longtime");
            return (Criteria) this;
        }

        public Criteria andMpicIsNull() {
            addCriterion("mpic is null");
            return (Criteria) this;
        }

        public Criteria andMpicIsNotNull() {
            addCriterion("mpic is not null");
            return (Criteria) this;
        }

        public Criteria andMpicEqualTo(String value) {
            addCriterion("mpic =", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicNotEqualTo(String value) {
            addCriterion("mpic <>", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicGreaterThan(String value) {
            addCriterion("mpic >", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicGreaterThanOrEqualTo(String value) {
            addCriterion("mpic >=", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicLessThan(String value) {
            addCriterion("mpic <", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicLessThanOrEqualTo(String value) {
            addCriterion("mpic <=", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicLike(String value) {
            addCriterion("mpic like", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicNotLike(String value) {
            addCriterion("mpic not like", value, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicIn(List<String> values) {
            addCriterion("mpic in", values, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicNotIn(List<String> values) {
            addCriterion("mpic not in", values, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicBetween(String value1, String value2) {
            addCriterion("mpic between", value1, value2, "mpic");
            return (Criteria) this;
        }

        public Criteria andMpicNotBetween(String value1, String value2) {
            addCriterion("mpic not between", value1, value2, "mpic");
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