package com.dunkware.trade.net.data.server.stream.entitystats.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.CacheHelper;
import com.dunkware.trade.net.data.server.stream.entitystats.cache.loader.StatCacheLoaderHelper;
import com.dunkware.xstream.model.stats.entity.EntityStat;

public class EntityStatFileWriter {
	
	
	
	public static EntityStatFileWriter newInstance(String directory, String fileName, boolean create) throws Exception {
		return new EntityStatFileWriter(directory,fileName, create);
	}
	
	
	
	StringBuilder builder = new StringBuilder();
	FileWriter writer = null;
	BufferedWriter bwr = null; 
	PrintWriter pw = null;
	
	private long writeCount = 0;
	
	private EntityStatFileWriter(String directory, String fileName, boolean create) throws Exception { 
		
		File file = new File(directory + "/" + fileName);
		
		if(!file.exists() && !create) { 
			throw new Exception("File does ont exist, create = false " + directory + "/" + fileName);
		}
		
		File fileDirectory = new File(directory);
		if(fileDirectory.exists() == false) { 
			boolean fuck = fileDirectory.mkdirs();
			if(fuck == false) { 
				throw new Exception("False returned on making directory " + directory);
			}
		}
		if(file.exists() == false) { 
			boolean results = file.createNewFile();
			
			if(!results) { 
				throw new Exception("Exception creating new file " + " " + fileName);
			}
			if(file.exists() == false || file.isFile() == false) { 
				throw new Exception("creating new file is not a file or does not exist");
			}
		}
		
		
		 writer = new FileWriter(file);
		 bwr = new BufferedWriter(writer);
		 pw = new PrintWriter(bwr);
	}
	
	
	public void writeStat(EntityStat stat) { 
		builder.setLength(0);
		builder.append(CacheHelper.dateKey(stat.getDate()));
		builder.append(",");
		builder.append(stat.getEntity());
		builder.append(',');
		builder.append(stat.getElement());
		builder.append(',');
		builder.append(stat.getStat());
		builder.append(',');
		builder.append(stat.getValue().doubleValue());
		builder.append(',');
		if(stat.getTime() == null) { 
			builder.append(-1);
		} else { 
			builder.append(DunkTime.toLocalTimeLong(stat.getTime()));
		}
		pw.println(builder.toString());
	}
	
	
	public void close() { 
		try {
			pw.close();
			bwr.close();
			writer.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	
	
	

}
