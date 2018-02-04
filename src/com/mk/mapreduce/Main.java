package com.mk.mapreduce;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Main {

	public static void main(String[] args) throws Exception{
		
		String basePath="hdfs://master:9000/ntd";
		String importPath=basePath+"/input/remark";
		String inputPath=basePath+"/input/filter";
		String itemUserTableOuputPath=basePath+"/output/itemUserTable";
		String userItemTableOutputPath=basePath+"/output/userItemTable";
		String userCFMatrixOutputPath=basePath+"/output/userCFMatrix";
		String userCFOutputPath=basePath+"/output/userCF";
		String itemCFOutputPath=basePath+"/output/itemCF";
		
		/*
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(importPath),conf);
		if(fs.exists(new Path(importPath)))
				fs.delete(new Path(importPath));
		*/
		
		/*
		SqoopUtil.import_data(importPath);
		System.out.println("import successful");
		*/
		
		String[] splitChars={Config.SPLIT_CHAR};
		/*

		splitChars=Filter.run(importPath, inputPath, Config.SPLIT_CHAR);
		System.out.println("filter successful");
		*/

		/*
		UserCF.run(inputPath,userCFOutputPath,Config.SPLIT_CHARS,splitChars);
		System.out.println("UserCF successful");
	    */
		/*
		ItemCF.run(inputPath,itemCFOutputPath,Config.SPLIT_CHARS,splitChars);
		System.out.println("UserCF successful");
	    */
	    
		SqoopUtil.export_data(userCFMatrixOutputPath+"3",Config.USER_TABLE);
		System.out.println("export user_table successful");
		
		SqoopUtil.export_data(itemCFOutputPath+"3",Config.PHONE_TABLE);
		System.out.println("export user_table successful");
		
		SqoopUtil.export_data(itemCFOutputPath,Config.ITEM_TABLE);
		System.out.println("export itemTable successful");
		
	}
}
