package com.yq.accountsoft.model;

public class Tb_pwd
{
	private String account;
	private String password;//
	public Tb_pwd()//
	{
		super();
	}

	public Tb_pwd(String account,String password)//
	{
		super();
		this.password = password;
		this.account=account;
		// Ϊ���븳ֵ
	}

	public String getPassword()// ��������Ŀɶ�����
	{
		return password;
	}

	public void setPassword(String password)// ��������Ŀ�д����
	{
		this.password = password;
	}
	public String getAccount(){
		return account;
	}
	public void setAccount(String account){
		this.account=account;
	}
}
