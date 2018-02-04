package com.mk.mapreduce;

import java.net.URI;
import java.util.ArrayList;
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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;


public class Cooccurrence_2 {
	static class MyMapper  extends  Mapper<LongWritable, Text, Text, Text>{  

		 public void map(LongWritable k1, Text v1, Context context) 
						 throws java.io.IOException, java.lang.InterruptedException
		 {
			// A1-A2  size-sum
			String[] lines=v1.toString().split(existSplitChars[0]);
			// A1  A2-size-sum
		    context.write(new Text(lines[0]),new Text(lines[1]+splitChars[0]+lines[2]+splitChars[0]+lines[3]));	
		
		 }
		
	}
	
	static class  MyReduce extends Reducer<Text, Text, Text, NullWritable>{

		public void reduce(Text k2, Iterable<Text> v2, Context context) throws java.io.IOException, java.lang.InterruptedException
		 {
			ArrayList<String> list=new ArrayList<>();
			int total=0;
			for (Iterator<Text> iterator = v2.iterator(); iterator.hasNext();) {
				//A2 size sum
				Text value=iterator.next();
				String size1=value.toString().split(splitChars[0])[1];
				int size=Integer.parseInt(size1);
				total+=size;
			    list.add(value.toString());
			}
			
			for(String value:list){
				
				//A1-A2 size-sun-total1
				 context.write(new Text(k2.toString()+splitChars[0]+value+splitChars[0]+total), NullWritable.get());
			}
			 
		 }		    
	}
	
	static String[] splitChars;
	static String[] existSplitChars;

	public static String[] run(String INPUT_PATH,String OUTPUT_PATH,String[] splitChars,String[] existSplitChars) throws  Exception {
		Cooccurrence_2.splitChars=splitChars;
		Cooccurrence_2.existSplitChars=existSplitChars;
		
		Configuration  conf=new Configuration();
		FileSystem  fs=FileSystem.get(new URI(OUTPUT_PATH),conf);
	 
		if(fs.exists(new Path(OUTPUT_PATH)))
				fs.delete(new Path(OUTPUT_PATH));
		
		Job  job=new Job(conf,"myjob");
		
		job.setJarByClass(Cooccurrence_2.class);
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
		
		return new String[]{splitChars[0]};

	}
}
