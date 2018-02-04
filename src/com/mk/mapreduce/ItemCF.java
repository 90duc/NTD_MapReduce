package com.mk.mapreduce;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ItemCF {

	public static void run(String INPUT_PATH,String OUTPUT_PATH,String[] splitChars,String[] existSplitChars) throws Exception{
		
		String inputPath = INPUT_PATH;
		String outputPath=OUTPUT_PATH+"0";
		String[][] existSplitCharsArray=new String[2][];
		existSplitCharsArray[1]=existSplitChars;
		
		
		existSplitChars=UserItemTable.run(inputPath,outputPath,Config.SPLIT_CHARS,splitChars);
		System.out.println("ItemUserTable successful");
		
	
		inputPath=outputPath;
		outputPath=OUTPUT_PATH+"1";
		existSplitChars=Cooccurrence_1.run(inputPath,outputPath,splitChars,existSplitChars,true);
		System.out.println("ItemCF_1 successful");
	
		
		
		inputPath=outputPath;
		outputPath=OUTPUT_PATH+"2";
		existSplitChars=Cooccurrence_2.run(inputPath,outputPath,splitChars,existSplitChars);
		System.out.println("ItemCF_2 successful");
		
		inputPath=outputPath;
		outputPath=OUTPUT_PATH+"3";
		existSplitChars=Cooccurrence_3.run(inputPath,outputPath,splitChars,existSplitChars);
		System.out.println("ItemCF_3 successful");
		
		existSplitCharsArray[0]=existSplitChars;
		Matrix.run(new String[] { outputPath, INPUT_PATH }, OUTPUT_PATH, Config.SPLIT_CHARS, existSplitCharsArray,new int[]{0,1});
		
	}
}
