package com.nbui.policy.condition;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/**
 * 个人中心我的保险以及我的保单的条件类
 * @author xinxin
 *
 */
@Component
public class PolicyBriefCondition implements Serializable{
	private static final long serialVersionUID = -868972513111843935L;
	private Integer id;//用户id
	private String policyCreateDate;//订单生效时间
	private String policystatus1;//订单状态
	private String policystatus2;//保单状态
	private String search1;//搜索框输入内容(订单号(保单),保险名称,汽车名,被保人)
	private String search2;//搜索框输入内容(保单号(保单),保险名称,汽车名,被保人)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getPolicyCreateDate() {
		return policyCreateDate;
	}
	public void setPolicyCreateDate(String policyCreateDate) {
		this.policyCreateDate = policyCreateDate;
	}
	public String getPolicystatus1() {
		return policystatus1;
	}
	public void setPolicystatus1(String policystatus1) {
		this.policystatus1 = policystatus1;
	}
	public String getPolicystatus2() {
		return policystatus2;
	}
	public void setPolicystatus2(String policystatus2) {
		this.policystatus2 = policystatus2;
	}
	public String getSearch1() {
		return search1;
	}
	public void setSearch1(String search1) {
		this.search1 = search1;
	}
	public String getSearch2() {
		return search2;
	}
	public void setSearch2(String search2) {
		this.search2 = search2;
	}
	@Override
	public String toString() {
		return "PolicyBriefCondition [id=" + id + ", policyCreateDate=" + policyCreateDate + ", policystatus1="
				+ policystatus1 + ", policystatus2=" + policystatus2 + ", search1=" + search1 + ", search2=" + search2
				+ "]";
	}
	
	
	
	
}
