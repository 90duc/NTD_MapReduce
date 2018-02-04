package com.mk.mapreduce;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MatrixMulti {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  

		String matrix;
		public void setup(Context context){
			String path=((FileSplit) context.getInputSplit()).getPath().toString();
			
			if(path.startsWith(inputPaths[0])){
				matrix="A";
 
			}else if(path.startsWith(inputPaths[1])){
				matrix="B";
    
			}else {
				System.out.println("file name error");
				throw new RuntimeException("file name error");
			}
		}
		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			
			context.write(new Text("1"),new Text(matrix+existSplitChars[0]+v1.toString()));	
			
		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{

		public void reduce(Text u2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			ArrayList<String> A=new ArrayList<>();
			ArrayList<String> B=new ArrayList<>();
			ArrayList<String> M;
			for (Text text : v2) {
				// matrix user ----
				String[] data=text.toString().split(existSplitChars[0]);
				if(data[0].equals("A")){
					M=A;
				}else{
					M=B;
				}
				M.add(data[1]+existSplitChars[0]+data[2]);
			}
			
			for(String a : A){
				String[] dataA=a.split(existSplitChars[0]);
				for(String b : B){
					String[] dataB=b.split(existSplitChars[0]);
				   // System.out.println(Arrays.toString(dataA)+" "+Arrays.toString(dataB));
					String result=dataA[0]+splitChars[0]+dataB[0]+splitChars[0]+calc(dataA[1], dataB[1]);
					//user item v
					context.write(new Text(result), NullWritable.get());
				}
			}
			 
		 }		
		
		double calc(String rowA,String rowB){
			//user2 v;
			String[] dataA=rowA.split(existSplitChars[1]);
			//user2 r;
			String[] dataB=rowB.split(existSplitChars[1]);
			
			HashMap<String, String> map=new HashMap<>();
			double sum=0;
			for(String a : dataA){
				String[] value=a.split(existSplitChars[2]);
				map.put(value[0], value[1]);
			}
	
			for(String b : dataB){
				String[] rank=b.split(existSplitChars[2]);
				String value=map.get(rank[0]);
				if(value!=null)
				 sum+=Double.parseDouble(value)*Double.parseDouble(rank[1]);
			}
			return sum;
		}
	}
	
	static String[] splitChars;
	static String[] existSplitChars;
	static String[] inputPaths;

	public static String[] run(String[] INPUT_PATHS,String OUTPUT_PATH,String[] splitChars,String[] existSplitChars) throws  Exception {
		MatrixMulti.splitChars=splitChars;
		MatrixMulti.existSplitChars=existSplitChars;
		MatrixMulti.inputPaths=INPUT_PATHS;
		
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(MatrixMulti.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
		//job.setCombinerClass(MySortReduce.class);
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATHS[0]));
		FileInputFormat.addInputPath(job,new Path(INPUT_PATHS[1]));
//		FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);
		
		return new String[]{splitChars[0]};

	}
}
