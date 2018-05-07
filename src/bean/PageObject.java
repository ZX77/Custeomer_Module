package bean;

import java.io.Serializable;

public class PageObject implements Serializable{
	
	private int rowCount;	    //记录总数
	private int pageSize=4;	//每页显示条数
	private int pageCount;	//总页数
	private int curPage=1;    //当前页
	private String opr="first";//默认第一页
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		//计算总页数
		pageCount=(int)Math.ceil((double)rowCount/(double)pageSize);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public String getOpr() {
		return opr;
	}
	public void setOpr(String opr) {
		if(opr.equals("first")){
			curPage = 1;
		}else if(opr.equals("next")){
			curPage = curPage+1;
			if(curPage > pageCount){
				curPage = pageCount;
			}
		}else if(opr.equals("prior")){
			curPage = curPage-1;
			if(curPage < 1){
				curPage = 1;
			}
		}else if(opr.equals("last")){
			curPage = pageCount;
		}

		this.opr = opr;
	}
}
