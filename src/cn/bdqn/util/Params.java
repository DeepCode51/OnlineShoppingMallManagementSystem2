package cn.bdqn.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 自己定义的查询工具类
 *
 * Created by bdqn on 2016/5/6.
 */
public class Params implements Serializable{

    private String tableName;

    //选择参数
    private Map<String,Object> selectParams;
    //模糊查询参数
    private Map<String,Object> likeParams;
    //更新参数
    private Map<String,Object> updateParams;
    //排序参数
    private Map<String,Object> sortParams;
    //新增参数
    private Map<String,Object> addParams;

    //查询起始位置
    private int startIndex;

    //查询数目
    private int pageSize;

    private boolean isOpenPager=false;

    public Params() {
        selectParams=new HashMap<String, Object>();
        sortParams=new HashMap<String, Object>();
        updateParams=new HashMap<String, Object>();
        addParams=new HashMap<String, Object>();
        likeParams=new HashMap<String, Object>();
    }
    public Params(String tableName) {
        this.tableName=tableName;
        selectParams=new HashMap<String, Object>();
        sortParams=new HashMap<String, Object>();
        updateParams=new HashMap<String, Object>();
        addParams=new HashMap<String, Object>();
        likeParams=new HashMap<String, Object>();
    }

    //增加查询参数
    public Params addEqual(String column, Object value){
        selectParams.put(column,value);
        return this;
    }
    public Params addLike(String column, Object value){
        likeParams.put(column,value);
        return this;
    }
    //增加设置参数
    public Params addSet(String column,Object value){
        updateParams.put(column,value);
        return this;
    }
    //设置排序
    public Params orderDesc(String cloumn){
        sortParams.put(cloumn,"desc");
        return this;
    }
    public Params orderAsc(String cloumn){
        sortParams.put(cloumn,"asc");
        return this;
    }
    //设置分页
    public Params openPager(int startIndex,int pageSize){
        this.isOpenPager=true;
        this.startIndex=startIndex;
        this.pageSize=pageSize;
        return this;
    }
    public Params addSaveParam(String column,Object value){
        addParams.put(column,value);
        return this;
    }

    public Map<String, Object> getLikeParams() {
        return likeParams;
    }

    public void setLikeParams(Map<String, Object> likeParams) {
        this.likeParams = likeParams;
    }

    public boolean isOpenPager() {
        return isOpenPager;
    }

    public void setOpenPager(boolean isOpenPager) {
        this.isOpenPager = isOpenPager;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getSelectParams() {
        return selectParams;
    }

    public void setSelectParams(Map<String, Object> selectParams) {
        this.selectParams = selectParams;
    }

    public Map<String, Object> getUpdateParams() {
        return updateParams;
    }

    public void setUpdateParams(Map<String, Object> updateParams) {
        this.updateParams = updateParams;
    }

    public Map<String, Object> getSortParams() {
        return sortParams;
    }

    public void setSortParams(Map<String, Object> sortParams) {
        this.sortParams = sortParams;
    }

    public Map<String, Object> getAddParams() {
        return addParams;
    }

    public void setAddParams(Map<String, Object> addParams) {
        this.addParams = addParams;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
