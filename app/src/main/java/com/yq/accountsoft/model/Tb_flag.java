package com.yq.accountsoft.model;

import java.util.Calendar;

public class Tb_flag//
{
	private String id;//
	private String flag;//
	private String name;
	private static String time;
	private static  int mYear;// 年
	private static int mMonth;// 月
	private static int mDay;// 日
	public Tb_flag()//
	{
		super();
	}

	//
	public Tb_flag(String id, String name,String time ,String flag) {
		super();
		this.id = id;//
		this.flag = flag;//
		this.name=name;
		this.time=time;
	}

	public String getId()//
	{
		return id;
	}

	public void setId(String id)//
	{
		this.id = id;
	}

	public String getFlag()//
	{
		return flag;
	}

	public void setFlag(String flag)//
	{
		this.flag = flag;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public static  String getTime(){
		final Calendar calendar=Calendar.getInstance();
		mYear=calendar.get(Calendar.YEAR);
		mMonth=calendar.get(Calendar.MONTH);
		mDay=calendar.get(Calendar.DAY_OF_MONTH);
		time=new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay).toString();
		return time;
	}


}
