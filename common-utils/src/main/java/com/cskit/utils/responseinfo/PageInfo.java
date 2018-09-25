package com.cskit.utils.responseinfo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

/**
 * @author Micro
 * @Title: Cookie操作
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/19 19:12
 */
@ApiModel(value = "数据页信息")
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 8656597559014685635L;

    public PageInfo(List<T> list, int pageNum, int pageSize, long total, int pages, int size) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.size = size;
    }

    private List<T> list;
    private int pageNum = 0;
    private int pageSize = 10;
    private long total = 0;
    private int pages = 0;
    private int size = 0;
//    private Integer pageNumber = 1;

    public PageInfo() {
    }


    @ApiModelProperty(value = "总页数")
    public int getPages() {
        if (this.pages > 0)
            return pages;
        else if (this.total > 0) {
            return (int) ((this.total + this.pageSize - 1) / pageSize);
        } else
            return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    @ApiModelProperty(value = "当前页数据数")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public PageInfo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
        }
    }

    @ApiModelProperty(value = "数据列表")
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @ApiModelProperty(value = "页索引")
    public int getPageNum() {
        return pageNum;
    }

    public void setPage(int pageNum) {
        this.pageNum = pageNum;
    }

    @ApiModelProperty(value = "每页几条数据")
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @ApiModelProperty(value = "总记录数")
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

/*    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }*/
}
