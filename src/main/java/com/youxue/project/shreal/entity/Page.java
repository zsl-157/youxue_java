package com.youxue.project.shreal.entity;

public class Page {
    int pageNum;
    int rows;
    int offet;

    public Page(){
        this.pageNum = 1;
        this.rows = 10;
        this.offet = 1;
    }

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffet() {
        return offet;
    }

    public void setOffet(int offet) {
        this.offet = offet;
    }
}
