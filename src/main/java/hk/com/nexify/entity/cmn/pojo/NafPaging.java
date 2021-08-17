package hk.com.nexify.entity.cmn.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import hk.com.nexify.cmn.util.XssValidation;

@XmlType(name = "nafPaging")
public final class NafPaging implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNo;
    private int pageSize = 10;
    private String sortBy;
    private boolean sortDesc;

    public NafPaging() {
    }

    public NafPaging(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public NafPaging(int pageNo, int pageSize, String sortBy, boolean sortDesc) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDesc = sortDesc;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = XssValidation.stripXSS(sortBy);
    }

    public boolean isSortDesc() {
        return sortDesc;
    }

    public void setSortDesc(boolean sortDesc) {
        this.sortDesc = sortDesc;
    }

    @Override
    public String toString() {
        return "pageNo:" + pageNo + ",pageSize:" + pageSize + ",sortBy:" + sortBy + ",sortDesc:" + sortDesc;
    }

}
