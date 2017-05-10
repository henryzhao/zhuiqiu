package com.eamon.zhuiqiu.util.captcha;


public abstract class SenderWapper {

	protected DataStore requestData = new DataStore();

	public abstract ISender getSender();
}
