package com.woniu.orders.entity;

import java.util.Date;
import java.util.List;


import com.woniu.orders.util.Seat;


public class Order {
    /**
     * 订单id
     */
    private Integer id;
    private String longtime;

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    //差额
    private  Double balanceMoney;

    public String getLongtime() {
        return longtime;
    }

    public void setLongtime(String longtime) {
        this.longtime = longtime;
    }

    /**
     * 用户id
     */
    private Integer uid;
    private Integer mid;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * 订单创建时间
     */
    private Date cTime;

    /**
     * 订单金额
     */
    private Vip vip;

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    private Double money;
    private Double oldMoney;

    public Double getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(Double oldMoney) {
        this.oldMoney = oldMoney;
    }

    /**
     * 放映点id
     */
    private Integer cid;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 影院id
     */
    private Integer aid;

    /**
     * 订单状态，
     */
    private byte ostate;

    public String getcFormatTime() {
        return cFormatTime;
    }

    public void setcFormatTime(String cFormatTime) {
        this.cFormatTime = cFormatTime;
    }

    private String cFormatTime;
    public String getOstateMsg() {
        return ostateMsg;
    }

    public void setOstateMsg(String ostateMsg) {
        this.ostateMsg = ostateMsg;
    }

    private String ostateMsg;

    /**
     * 支付状态,待支付，已支付，退款，
     */
    private Byte payState;

    /**
     * 支付方式
     */
    private Byte payway;

    /**
     * 二维码
     */
    private String code;

    /**
     * 订单号
     */
    private String orderId;
    private String oldOrderId;

    public String getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(String oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    //座位号
    private List<Seat> seatList;
    private String seat;
    //影院名
    private String cinemaName;
    //海报
    private String posterUrl;
    //影厅名
    private String roomName;
    //播放时间
    private Date palyTime;
    //电影名
    private String movieName;
    //影院地址
    private  String cinemaAddress;
    //电话
    private  String phone;
    //放映表id
    private Integer msid;
    //座位id
    private  String seatId;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public void setOstate(byte ostate) {
        this.ostate = ostate;
    }

    public Integer getMsid() {
        return msid;
    }

    public void setMsid(Integer msid) {
        this.msid = msid;
    }

    public int  getLeftPaySecond() {
        return leftPaySecond;
    }

    public void setLeftPaySecond(int leftPaySecond) {
        this.leftPaySecond = leftPaySecond;
    }

    //剩余支付时间
    private int leftPaySecond;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public byte getOstate() {
        return ostate;
    }

    public void setOstate(Byte ostate) {
        this.ostate = ostate;
    }

    public Byte getPayState() {
        return payState;
    }

    public void setPayState(Byte payState) {
        this.payState = payState;
    }

    public Byte getPayway() {
        return payway;
    }

    public void setPayway(Byte payway) {
        this.payway = payway;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public Date getPalyTime() {
        return palyTime;
    }

    public void setPalyTime(Date palyTime) {
        this.palyTime = palyTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }
    //格式化的播放时间
    private String formatTime;

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", cTime=" + cTime +
                ", vip=" + vip +
                ", money=" + money +
                ", oldMoney=" + oldMoney +
                ", cid=" + cid +
                ", aid=" + aid +
                ", ostate=" + ostate +
                ", cFormatTime='" + cFormatTime + '\'' +
                ", ostateMsg='" + ostateMsg + '\'' +
                ", payState=" + payState +
                ", payway=" + payway +
                ", code='" + code + '\'' +
                ", orderId='" + orderId + '\'' +
                ", oldOrderId='" + oldOrderId + '\'' +
                ", seatList=" + seatList +
                ", seat='" + seat + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", roomName='" + roomName + '\'' +
                ", palyTime=" + palyTime +
                ", movieName='" + movieName + '\'' +
                ", cinemaAddress='" + cinemaAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", msid=" + msid +
                ", seatId='" + seatId + '\'' +
                ", leftPaySecond=" + leftPaySecond +
                ", username='" + username + '\'' +
                ", formatTime='" + formatTime + '\'' +
                '}';
    }
}