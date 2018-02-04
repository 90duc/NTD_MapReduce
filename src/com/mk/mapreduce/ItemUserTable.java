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


public class ItemUserTable {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  
		
		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			 //item user rank
			String[] lines=v1.toString().split(existSplitChars[0]);
			context.write(new Text(lines[0]), new Text(lines[1]+splitChars[0]+lines[2]));
		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{
		
		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			StringBuilder value2=new StringBuilder();
			int size=0;
			for(Text value:v2){
				value2.append(value.toString()+splitChars[1]);
				size++;
			}
		    //item size user-ramk;user-rank;
			context.write(new Text(k2.toString()+splitChars[2]+size+splitChars[2]+value2.toString()),NullWritable.get());
		 }

		    
	}
	
	static String[] splitChars;
	static String[] existSplitChars;

	public static String[] run(String INPUT_PATH,String OUTPUT_PATH,String[] splitChars,String[] existSplitChars) throws  Exception {
		
		ItemUserTable.splitChars=splitChars;
		ItemUserTable.existSplitChars=existSplitChars;
		
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(ItemUserTable.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
		//job.setCombinerClass(MySortReduce.class);
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class); 
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
//		FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);
		
		return new String[]{splitChars[2],splitChars[1],splitChars[0]};

	}
}
