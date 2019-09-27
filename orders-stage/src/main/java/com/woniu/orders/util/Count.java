package com.woniu.orders.util;

import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-24 16:42
 **/
public class Count {
    private List<CountDetail> finish;

    private List<CountDetail> refund;


    private List<CountDetail>  turnover;

    public List<CountDetail> getTurnover() {
        return turnover;
    }

    public void setTurnover(List<CountDetail> turnover) {
        this.turnover = turnover;
    }

    public List<CountDetail> getFinish() {
        return finish;
    }

    public void setFinish(List<CountDetail> finish) {
        this.finish = finish;
    }

    public List<CountDetail> getRefund() {
        return refund;
    }

    public void setRefund(List<CountDetail> refund) {
        this.refund = refund;
    }


}
