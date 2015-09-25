package com.sunwayland.core.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.web.vo.Global;
 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebPage {
	 
	@Range
	private  Integer total ; 
	 
	@NotNull    
	private  Integer currentPage  = 1  ;
	 
	@NotNull  
	private  Integer  itemsPerPage  = 100 ;
	
	private  Object  data ; 
	
	 
		
	public Integer getLimit() {
		return   this.itemsPerPage ; 
	}
	   
	public Integer getOffset() { 
		 return (this.currentPage -1)*this.itemsPerPage ; 
		 
	}
	 
	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public Object getData() {
		return data;
	}
	
	 
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	/**
	 * 最多200 条; 
	 * @param itemsPerPage
	 */
	public void setItemsPerPage(Integer itemsPerPage) { 
		
		this.itemsPerPage = itemsPerPage > Global.max_ItemsPerPage? Global.max_ItemsPerPage : itemsPerPage;
		
	} 
	
	
	
	

}
