package net.zhenghao.zh.common.entity;

import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 *Mybatis分页参数及查询结果封装,注意所有序号从1开始
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月20日 下午3:39:18
 * Page.java
 */
public class Page<T> extends RowBounds {
	
	/**
	 * 页编号:第几页
	 */
	protected int pageNum = 1;
	
	/**
	 * 页大小:每页的数量
	 */
	protected int pageSize = 10;
	
	/**
	 * 偏移量:第一条数据在表中的位置
	 */
	protected int offset;
	
	/**
	 * 限定数:每页的数量
	 */
	protected int limit;
	
	// --结果-- //
	/**
	 * 查询结果
	 */
	protected List<T> data = new ArrayList<>();
	
	/**
	 * 总条数
	 */
	protected int total;
	
	/**
	 * 总页数
	 */
	protected int totalPages;
	
	/**
	 * 计算偏移量
	 */
	private void calcOffset(){
		this.offset = ((pageNum - 1) * pageSize);
	}
	
	/**
	 * 计算限定数
	 */
	private void calcLimit(){
		this.limit = pageSize;
	}
	
	public Page(){
		this.calcOffset();
		this.calcLimit();
	}
	
	public Page(int pageNum, int pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.calcOffset();
		this.calcLimit();
	}
	
	public Page(Query search){
		this.pageNum = search.getAsInt("pageNum") == null ? this.pageNum : search.getAsInt("pageNum");
		this.pageSize = search.getAsInt("pageSize") == null ? this.pageSize : search.getAsInt("pageSize");
		this.calcOffset();
		this.calcLimit();
	}
	
	/**
	 * 获得当前页面的页号,序号从1开始,默认为1
	 * @return
	 */
	public int getPageNum(){
		return pageNum;
	}
	
	/**
     * 获得每页的记录数量,默认为1.
     */
    public int getPageSize() {
        return pageSize;
    }
    
    /**
     * 根据pageNum和pageSize计算当前页第一条记录在总结果集中的位置
     * @return
     */
    public int getFirst(){
    	return ((pageNum - 1) * pageSize) + 1;
    }
    
    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */
    @Override
    public int getOffset() {
        return offset;
    }

	@Override
    public int getLimit() {
        return limit;
    }
    
    /**
     * 取得页内的记录列表
     * @return
     */
    public List<T> getData(){
    	return data;
    }
    
    /**
     * 设置页内的记录列表.
     */
    public void setData(final List<T> data) {
        this.data = data;
    }
    
    /**
     * 取得总记录数, 默认值为-1.
     */
    public int getTotal() {
        return total;
    }
    
    /**
     * 设置总记录数.
     */
    public void setTotal(final int total) {
        this.total = total;
        this.totalPages = this.getTotalPages();
    }
    
    /**
     * 根据pageSize与total计算总页数, 默认值为-1.
     */
    public int getTotalPages(){
    	if (total < 0) {
			return -1;
		}
    	int pages = total / pageSize;
    	return total % pageSize > 0 ? ++pages : pages;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
