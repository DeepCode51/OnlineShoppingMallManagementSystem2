package cn.bdqn.params;

import cn.bdqn.entity.OrderDetail;

public class OrderDetailParam extends OrderDetail{
    //查询的起始页
	private Integer startIndex;
	//每页显示几条
	private Integer pageSize;
	//是否分页
	private boolean isPage=false;
	//排序用的是那一列
	private String sort;
	
	public void openPage(Integer startIndex, Integer pageSize) {
		this.isPage = true;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isPage() {
		return isPage;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	
	
}
