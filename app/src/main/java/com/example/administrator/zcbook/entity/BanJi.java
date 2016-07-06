package com.example.administrator.zcbook.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/5.
 */
public class BanJi implements Serializable {
    private int banJiId;
    private String banJiName;
    private int banJiPer;
    private int bookCount;

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getBanJiId() {
        return banJiId;
    }

    public void setBanJiId(int banJiId) {
        this.banJiId = banJiId;
    }

    public String getBanJiName() {
        return banJiName;
    }

    public void setBanJiName(String banJiName) {
        this.banJiName = banJiName;
    }

    public int getBanJiPer() {
        return banJiPer;
    }

    public void setBanJiPer(int banJiPer) {
        this.banJiPer = banJiPer;
    }
}
