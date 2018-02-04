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


public class Filter {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, NullWritable>{  
		
		long sum=0;
		int i=0;
		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			String[] lines=v1.toString().split(splitChar);
			
			String info=lines[0]+splitChar+lines[1]+splitChar+lines[4];
		    //item user rank
			context.write(new Text(info), NullWritable.get());
		 }

	}
	
	static String splitChar;

	public static String[] run(String INPUT_PATH,String OUTPUT_PATH,String splitChar) throws  Exception {
		
		Filter.splitChar=splitChar;
		
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(Filter.class);
		job.setMapperClass(MyMapper.class);
		
		 
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class); 
		
		
		FileInputFormat.addInputPath(job,new Path(INPUT_PATH));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));
		
		job.waitForCompletion(true);
		return new String[]{splitChar};

	}
}
