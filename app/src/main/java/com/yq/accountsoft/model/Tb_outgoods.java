package com.yq.accountsoft.model;

public class Tb_outgoods//
{
	private String id;// 进货编号
	private String name;//进货名称
	private String money;// 进货单价
	private String many;//进货数量
	private String time;// 进货时间
	private String type;// 进货类别
	private String handler;// 经手人
	private String mark;// 备注
	private double amount;//总计金额
	public Tb_outgoods()//
	{
		super();
	}

	//
	public Tb_outgoods(String id, String name, String money, String many, String time, String type,
					   String handler, String mark) {
		super();
		this.id = id;// 为收入编号赋值
		this.name=name;
		this.money = money;// 为收入金额赋值
		this.many=many;
		this.time = time;// 为收入时间赋值
		this.type = type;// 为收入类别赋值
		this.handler = handler;// 为收入付款方赋值
		this.mark = mark;// 为收入备注赋值
	}

	public String getId()// 设置收入编号的可读属性
	{
		return id;
	}

	public void setId(String id)// 设置收入编号的可写属性
	{
		this.id = id;
	}
	public String getName()// 设置收入编号的可读属性
	{
		return name;
	}

	public void setName(String name)// 设置收入编号的可写属性
	{
		this.name = name;
	}

	public String getMoney()// 设置收入金额的可读属性
	{
		return money;
	}

	public void setMoney(String money)// 设置收入金额的可写属性
	{
		this.money = money;
	}
	public String getMany(){

		return many;
	}
	public void setMany(String many){
		this.many=many;
	}

	public String getTime()// 设置收入时间的可读属性
	{
		return time;
	}

	public void setTime(String time)// 设置收入时间的可写属性
	{
		this.time = time;
	}

	public String getType()// 设置收入类别的可读属性
	{
		return type;
	}

	public void setType(String type)// 设置收入类别的可写属性
	{
		this.type = type;
	}

	public String getHandler()// 设置收入付款方的可读属性
	{
		return handler;
	}

	public void setHandler(String handler)// 设置收入付款方的可写属性
	{
		this.handler = handler;
	}

	public String getMark()// 设置收入备注的可读属性
	{
		return mark;
	}

	public void setMark(String mark)// 设置收入备注的可写属性
	{
		this.mark = mark;
	}
	/*
	public double getAmount(){

		return many*money;
	}
	*/

}
