package com.qgh.spring_mvc.moduels.bean;


import com.qgh.spring_mvc.common.util.page.Page;

public class BaseBean<T> {
	/**
	 * 渠道编码
	 */
	protected String channelCode;
	/**
	 * 页码
	 */
	protected int pageno;
	/**
	 * 页数
	 */
	protected int pagesize;
	/**
	 * 开始时间
	 */
	protected String startTime;
	/**
	 * 结束时间
	 */
	protected String endTime;
	/**分页对象*/
	protected Page<T> page;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
