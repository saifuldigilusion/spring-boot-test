package hk.com.nexify.entity.cmn.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class NafPageList<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private NafPaging paging;
	private List<T> resultList;
	private int totalRecordNo;

	public NafPaging getPaging() {
		return paging;
	}

	public void setPaging(NafPaging paging) {
		this.paging = paging;
	}

	public List<T> getResultList() {
		if (null == resultList) {
			resultList = new ArrayList<T>();
		}

		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public void addResult(T result) {
		if (null == resultList) {
			resultList = new ArrayList<T>();
		}

		resultList.add(result);
	}

	public void addAllResults(List<T> results) {
		if (null == resultList) {
			resultList = new ArrayList<T>();
		}

		resultList.addAll(results);
	}

	public int getTotalRecordNo() {
		return totalRecordNo;
	}

	public void setTotalRecordNo(int totalRecordNo) {
		this.totalRecordNo = totalRecordNo;
	}
	
	public int getTotalPageNo() {
		int totalPageNo=0;
		if ( (this.totalRecordNo % this.paging.getPageSize())  > 0 ) {
			totalPageNo = Math.round((this.totalRecordNo / this.paging.getPageSize() ) ) + 1;			
		}else {
			totalPageNo = (this.totalRecordNo / this.paging.getPageSize() );
		}		
		
		return totalPageNo;
	}
}
