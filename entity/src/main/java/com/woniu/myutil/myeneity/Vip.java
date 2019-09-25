package com.woniu.myutil.myeneity;

import java.io.Serializable;

public class Vip implements Serializable {
    /**
    * vip表id
    */
    private Integer id;

    /**
    * vip 的名称：普通VIP和svip
    */
    private String vname;

    /**
    * 折扣比例
    */
    private Double vdiscount;

    /**
    * 影院id
    */
    private Integer aid;

    /**
     * 影院折扣
     */
    private Double quota;

    /**
     * get and set
     * @return
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public Double getVdiscount() {
        return vdiscount;
    }

    public void setVdiscount(Double vdiscount) {
        this.vdiscount = vdiscount;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }
}