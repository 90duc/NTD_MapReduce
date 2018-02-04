package com.mk.mapreduce;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

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

public class Cooccurrence_1 {
	static class MyMapper extends Mapper<LongWritable, Text, Text, Text> {

		public void map(LongWritable k1, Text v1, Context context)
				throws java.io.IOException, java.lang.InterruptedException {
			// B size A-v; A-v
			String[] lines = v1.toString().split(existSplitChars[0]);
			String[] A_V = lines[2].split(existSplitChars[1]);
			for (int i = 0; i < A_V.length; i++) {
				String[] A_V_a = A_V[i].split(existSplitChars[2]);

				for (int j = 0; j < A_V.length; j++) {
					String[] A_V_b = A_V[j].split(existSplitChars[2]);
					if (!A_V_a[0].equals(A_V_b[0])) {
						//A1-A2 B-size
						context.write(new Text(A_V_a[0] + splitChars[0] + A_V_b[0]), new Text(lines[0]+splitChars[0]+lines[1]));

					}
				}
			}

		}

	}

	static class MyReduce extends Reducer<Text, Text, Text, NullWritable> {

		public void reduce(Text k2, Iterable<Text> v2, Context context)
				throws java.io.IOException, java.lang.InterruptedException {
			int size = 0;
			double sum=0;
			for (Text value : v2) {
				size++;
			}
			
			if (simpleCalculate) {
				sum=size;
			} else {
				HashMap<String, Integer> map = new HashMap<>();
				for (Text value : v2) {
					int psize=Integer.parseInt(value.toString().split(splitChars[0])[1]);
					sum += 1.0 / Math.log(1 + psize);
				}
				
			}
			//A1-A2 B-size-sum
			context.write(new Text(k2.toString() + splitChars[0] + size+splitChars[0]+sum), NullWritable.get());
		}
	}

	static String[] splitChars;
	static String[] existSplitChars;
	static boolean simpleCalculate;

	public static String[] run(String INPUT_PATH, String OUTPUT_PATH, String[] splitChars, String[] existSplitChars,
			boolean simpleCalculate) throws Exception {
		Cooccurrence_1.splitChars = splitChars;
		Cooccurrence_1.existSplitChars = existSplitChars;
		Cooccurrence_1.simpleCalculate = simpleCalculate;

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI(OUTPUT_PATH), conf);

		if (fs.exists(new Path(OUTPUT_PATH)))
			fs.delete(new Path(OUTPUT_PATH));

		Job job = new Job(conf, "myjob");

		job.setJarByClass(Cooccurrence_1.class);
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
		// job.setCombinerClass(MySortReduce.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.addInputPath(job, new Path(INPUT_PATH));
		// FileInputFormat.addInputPath(job,new Path(INPUT_PATH1));
		FileOutputFormat.setOutputPath(job, new Path(OUTPUT_PATH));

		job.waitForCompletion(true);

		return new String[] { splitChars[0]};

	}
}
