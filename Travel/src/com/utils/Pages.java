package com.utils;

public class Pages {
    private int prePage;//上一页
    private int nextPage;//下一页
    private int totalPage;//总页数

    private int currentPage;//当前页
    private int pageSize=3;//容量
    private int totalCount;//总记录数

    public int getPrePage() {
        if(getCurrentPage()>1){
            prePage=getCurrentPage()-1;
        }else {
            prePage=1;
        }
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        if(getCurrentPage()<getTotalPage()){
            nextPage=getCurrentPage()+1;
        }else {
            nextPage=getTotalPage();
        }
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        if(getTotalCount()%getPageSize()==0){
            totalPage=getTotalCount()/getPageSize();
        }else {
            totalPage=getTotalCount()/getPageSize()+1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
