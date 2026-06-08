package com.entity.vo;

import com.entity.ShangpinxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 商品信息
 * @author 
 * @email 
 * @date 2025-03-13 12:29:47
 */
public class ShangpinxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 商品分类
	 */
	
	private String shangpinfenlei;
		
	/**
	 * 商品图片
	 */
	
	private String shangpintupian;
		
	/**
	 * 商品规格
	 */
	
	private String shangpinguige;
		
	/**
	 * 商品品牌
	 */
	
	private String shangpinpinpai;
		
	/**
	 * 上架时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shangjiashijian;
		
	/**
	 * 商品介绍
	 */
	
	private String shangpinjieshao;
		
	/**
	 * 商家账号
	 */
	
	private String shangjiazhanghao;
		
	/**
	 * 商家名称
	 */
	
	private String shangjiamingcheng;
		
	/**
	 * 单限
	 */
	
	private Integer onelimittimes;
		
	/**
	 * 库存
	 */
	
	private Integer alllimittimes;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 价格
	 */
	
	private Double price;
		
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	
	private Integer onshelves;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：商品分类
	 */
	 
	public void setShangpinfenlei(String shangpinfenlei) {
		this.shangpinfenlei = shangpinfenlei;
	}
	
	/**
	 * 获取：商品分类
	 */
	public String getShangpinfenlei() {
		return shangpinfenlei;
	}
				
	
	/**
	 * 设置：商品图片
	 */
	 
	public void setShangpintupian(String shangpintupian) {
		this.shangpintupian = shangpintupian;
	}
	
	/**
	 * 获取：商品图片
	 */
	public String getShangpintupian() {
		return shangpintupian;
	}
				
	
	/**
	 * 设置：商品规格
	 */
	 
	public void setShangpinguige(String shangpinguige) {
		this.shangpinguige = shangpinguige;
	}
	
	/**
	 * 获取：商品规格
	 */
	public String getShangpinguige() {
		return shangpinguige;
	}
				
	
	/**
	 * 设置：商品品牌
	 */
	 
	public void setShangpinpinpai(String shangpinpinpai) {
		this.shangpinpinpai = shangpinpinpai;
	}
	
	/**
	 * 获取：商品品牌
	 */
	public String getShangpinpinpai() {
		return shangpinpinpai;
	}
				
	
	/**
	 * 设置：上架时间
	 */
	 
	public void setShangjiashijian(Date shangjiashijian) {
		this.shangjiashijian = shangjiashijian;
	}
	
	/**
	 * 获取：上架时间
	 */
	public Date getShangjiashijian() {
		return shangjiashijian;
	}
				
	
	/**
	 * 设置：商品介绍
	 */
	 
	public void setShangpinjieshao(String shangpinjieshao) {
		this.shangpinjieshao = shangpinjieshao;
	}
	
	/**
	 * 获取：商品介绍
	 */
	public String getShangpinjieshao() {
		return shangpinjieshao;
	}
				
	
	/**
	 * 设置：商家账号
	 */
	 
	public void setShangjiazhanghao(String shangjiazhanghao) {
		this.shangjiazhanghao = shangjiazhanghao;
	}
	
	/**
	 * 获取：商家账号
	 */
	public String getShangjiazhanghao() {
		return shangjiazhanghao;
	}
				
	
	/**
	 * 设置：商家名称
	 */
	 
	public void setShangjiamingcheng(String shangjiamingcheng) {
		this.shangjiamingcheng = shangjiamingcheng;
	}
	
	/**
	 * 获取：商家名称
	 */
	public String getShangjiamingcheng() {
		return shangjiamingcheng;
	}
				
	
	/**
	 * 设置：单限
	 */
	 
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	
	/**
	 * 获取：单限
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
				
	
	/**
	 * 设置：库存
	 */
	 
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	
	/**
	 * 获取：库存
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：是否上架(1:上架，0:下架)
	 */
	 
	public void setOnshelves(Integer onshelves) {
		this.onshelves = onshelves;
	}
	
	/**
	 * 获取：是否上架(1:上架，0:下架)
	 */
	public Integer getOnshelves() {
		return onshelves;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
