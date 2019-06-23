package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pageble{
	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;

	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.setSorter(sorter);
	}
	
	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		return (this.page != null & this.maxPageItem != null) ? ((this.page - 1) * this.maxPageItem) : null;
	}


	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	public Sorter getSorter() {
		return this.sorter != null ? this.sorter : null;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

}
