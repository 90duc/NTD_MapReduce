package com.mk.mapreduce;

public class Config{
	public static final String DRIVER="com.mysql.jdbc.Driver";
	public static final String HOST="192.168.23.1";
	public static final String PORT="3306";
	
	public static final String DTATBASE="ntd";
	public static final String URL="jdbc:mysql://"+Config.HOST+":"+Config.PORT+"/"+Config.DTATBASE;
	
	public static final String USER="root";
	public static final String PASSWORD="admin";
	
	public static final String TABLE="remark";
	public static final String USER_TABLE="user_recom";
	public static final String PHONE_TABLE="phone_recom";
	public static final String ITEM_TABLE="item_recom";
	
	public static final String[] SPLIT_CHARS={""+(char)1,""+(char)2,""+(char)3}; 
	public static final String SPLIT_CHAR = SPLIT_CHARS[0];
}