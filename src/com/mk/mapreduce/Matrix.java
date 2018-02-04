package com.mk.mapreduce;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Matrix {

	public static String[] run(String[] INPUT_PATH,String OUTPUT_PATH,String[] splitChars,String[][] existSplitCharsArrays,int[] index) throws Exception{
		
		String outputMatrix=OUTPUT_PATH+"Matrix";
		String outputPath=outputMatrix;
		String[] inputPath=new String[2];
		
		outputPath=outputMatrix+"1";
		String[] existSplitChars=GroupBy.run(INPUT_PATH[0],outputPath,splitChars,existSplitCharsArrays[0],index[0]);
		System.out.println("Matrix_1 successful");
		existSplitCharsArrays[0]=existSplitChars;
		inputPath[0]=outputPath;
		
		outputPath=outputMatrix+"2";
		existSplitChars=GroupBy.run(INPUT_PATH[1],outputPath,splitChars,existSplitCharsArrays[1],index[1]);
		System.out.println("Matrix_2 successful");
		existSplitCharsArrays[1]=existSplitChars;
		inputPath[1]=outputPath;
		
		outputPath=OUTPUT_PATH;
		existSplitChars=MatrixMulti.run(inputPath,outputPath,splitChars,existSplitChars);
		System.out.println("MatrixMulti successful");
		return existSplitChars;
		
	}
}
