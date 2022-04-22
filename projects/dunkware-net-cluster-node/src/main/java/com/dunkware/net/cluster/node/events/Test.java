package com.dunkware.net.cluster.node.events;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
public class Test {

public static void main(String[] args) {
	  Thread runner = new Thread() { 
		  public void run() { 
			  List<String> data = new ArrayList<String>();
			  while(true) { 
				  String me = "asdfsdafadsfsd,sdfdasfs,dsafds";
				  String[] split = me.split(",");
				  data.add(me);
			  }
		  }
	  };
	  runner.start();
	 
	  while(true) { 
		  try {
			Thread.sleep(1000);
			 OperatingSystemMXBean operatingSystemMXBean = 
			         (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			      System.out.println(operatingSystemMXBean.getSystemLoadAverage());
			      System.out.println(operatingSystemMXBean.getVersion());
			     
			      	System.out.println(memInfo());
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }
     }


public static String memInfo() {
	Runtime runtime = Runtime.getRuntime();
    NumberFormat format = NumberFormat.getInstance();
    StringBuilder sb = new StringBuilder();
    long maxMemory = runtime.maxMemory();
    long allocatedMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();
    sb.append("Free memory: ");
    sb.append(format.format(freeMemory / 1024));
    sb.append("<br/>");
    sb.append("Allocated memory: ");
    sb.append(format.format(allocatedMemory / 1024));
    sb.append("<br/>");
    sb.append("Max memory: ");
    sb.append(format.format(maxMemory / 1024));
    sb.append("<br/>");
    sb.append("Total free memory: ");
    sb.append(format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));
    sb.append("<br/>");
    return sb.toString();

}
}