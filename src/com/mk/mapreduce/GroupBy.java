package com.mk.mapreduce;

import java.net.URI;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.server.namenode.status_jsp;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class GroupBy {
	
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  
		
		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			 //A1  A2 v
			String[] lines=v1.toString().split(existSplitChars[0]);
			StringBuilder value=new StringBuilder();
			for(int i=0;i<lines.length;i++){
				if(i==index)
					continue;
				value.append(lines[i]+splitChars[0]);
			}
			if(value.length()>0)
				value.delete(value.length()-splitChars[0].length(), value.length());
			//A1 A2-v
			context.write(new Text(lines[index]), new Text(value.toString()));
			
		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{
		
		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			StringBuilder value2=new StringBuilder();
			for(Text value:v2){
				value2.append(value.toString()+splitChars[1]);
			}
		    //A1 A2-v;...
			context.write(new Text(k2.toString()+splitChars[2]+value2.toString()),NullWritable.get());
		 }

		    
	}
	
	static String[] splitChars;
	static String[] existSplitChars;
	static int index;

	public static String[] run(String INPUT_PATH,String OUTPUT_PATH,String[] splitChars,String[] existSplitChars,int index) throws  Exception {
		
		GroupBy.splitChars=splitChars;
		GroupBy.existSplitChars=existSplitChars;
		GroupBy.index=index;
		
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(GroupBy.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);
		
		return new String[]{splitChars[2],splitChars[1],splitChars[0]};

	}
}
