package net.zhenghao.zh.common.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 查询条件
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月20日 下午3:56:03
 * Query.java
 */
public class Query extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;
	
	public Query(){
		super();
	}
	
	public Query(Map<String, Object> params){
	    this.putAll(params);
	}
	
	public Double getAsDouble(String name) {
        Object value = this.get(name);
        if (value != null)
            return Double.valueOf(value.toString());
        return null;
    }

    public String getAsString(String name) {
        return this.get(name).toString();
    }

    public Long getAsLong(String name) {
        Object value = this.get(name);
        if (value != null)
            return Long.valueOf(value.toString());
        return null;
    }

    public Integer getAsInt(String name) {
        Object value = this.get(name);
        if (value != null)
            return Integer.valueOf(value.toString());
        return null;
    }

    public Boolean getAsBoolean(String name) {
	    Boolean bool = null;
        Object value = this.get(name);
        if (value != null)
            bool =  Boolean.valueOf(value.toString());
        return bool;
    }

    public java.util.Date getAsDate(String name) {
    	Object value = this.get(name);
        if (value != null)
        	return Date.valueOf(value.toString());
        return null;
    }

    public Object getObj(String name) {
        return this.get(name);
    }

    public void removePageParams() {
        Iterator<Entry<String, Object>> iterator = this.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            if ("pageSize".equals(key) || "pageNum".equals(key)) {
                iterator.remove();
            }
        }
    }
}
