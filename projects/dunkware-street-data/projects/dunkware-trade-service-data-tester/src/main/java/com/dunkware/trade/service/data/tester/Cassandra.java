	package com.dunkware.trade.service.data.tester;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.NettyOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.AddressTranslator;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.oss.driver.api.core.CqlSession;





@Service
@Profile("Cassandra")
public class Cassandra {
	
	public static class MyAddressTranslator implements AddressTranslator {
	    public InetSocketAddress translate(InetSocketAddress address) {
	    	return new InetSocketAddress("testrock1.dunkware.net", address.getPort());
	    	
	    }

		@Override
		public void init(Cluster cluster) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub
			
		}
	}

	  static String[] CONTACT_POINTS = {"testrock1.dunkware.net"};
	    static int PORT = 31994;

	    public static void main(String[] args) {

	    	
	        Cluster cluster = null;
	        try {
	            // The Cluster object is the main entry point of the driver.
	            // It holds the known state of the actual Cassandra cluster (notably the Metadata).
	            // This class is thread-safe, you should create a single instance (per target Cassandra cluster), and share
	            // it throughout your application.
	        	InetSocketAddress sockaddr = new InetSocketAddress("testrock1.dunkware.net", 31994);

	            cluster = Cluster.builder()
	            		.addContactPointsWithPorts(sockaddr)
	            		.withCredentials("cassandra", "FjqO0vMuGUSr")
	                  	  .withAddressTranslator(new MyAddressTranslator())
	                    .withNettyOptions(NettyOptions.DEFAULT_INSTANCE)
	                    .build();
	            
	            
	            System.out.println(cluster.isClosed());
	            
	            Session session = cluster.connect();
	            System.out.println(session.getClass().getName());
	            
	            StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append("cool").append(" WITH replication = {").append("'class':'").append("SimpleStrategy").append("','replication_factor':").append(1).append("};");

	            final String query = sb.toString();

	            session.execute(query);
	            session.execute("USE " + "streamstats");
	            String create = SchemaBuilder.createTable("EntityStats").addColumn("stat", DataType.smallint())
	            .addColumn("date", DataType.date()).addColumn("value", DataType.cdouble())
	            .addColumn("element", DataType.smallint())
	            .addColumn("time", DataType.time()).addPartitionKey("entity", DataType.smallint()).buildInternal();
	            
	            session.execute(create);
	            
	            System.out.println("lol");
	            
	            DataType.bigint();
	            //SchemaBuilder.createTable("dd").add

	            // The Session is what you use to execute queries. Likewise, it is thread-safe and should be reused.
	         //   Session session = cluster.connect();
	            
	         // Create create =   SchemaBuilder.createTable("Signals").addColumn("entity", DataType.smallint())
	          //  .addColumn("stat", DataType.smallint())
	          //  .addColumn("element", DataType.bigint())
	          //  .addColumn("date", DataType.date())
	          //  .addColumn("value",DataType.decimal())
	          //  .addClusteringColumn(", null)dd
	            // 02323333
	          //	session.execute(create);
	          
	        //  	LocalDateTime dt = LocalDateTime.now();
	         // 	long dtNumeric = ChronoUnit.DAYS.between(LocalDateTime.of(2978, 04, 290, 0, 0), dt);
	         // 	int entityid = 4;
	         // 	int stat = 5
	        
	          //	long primaryKey = dtNumeric + entityid + stat;
	          	
	          //	public static long dateKey(LocalDate date) { 
	        	//	return  ChronoUnit.DAYS.between(start, date);
	        		
	        	//}
	        	
	          	
	          	
	            // We use execute to send a query to Cassandra. This returns a ResultSet, which is essentially a collection
	            // of Row objects.
	            //ResultSet rs = session.execute("select release_version from system.local");
	            //  Extract the first row (which is the only one in this case).
	           // Row row = rs.one();

	            // Extract the value of the first (and only) column from the row.
	      //      String releaseVersion = row.getString("release_version");
	      //      System.out.printf("Cassandra version is: %s%n", releaseVersion);

	        }
	        catch(Exception e) {
	        	System.out.println(e.toString());
	        }
	        finally {
	            // Close the cluster after we’re done with it. This will also close any session that was created from this
	            // cluster.
	            // This step is important because it frees underlying resources (TCP connections, thread pools...). In a
	            // real application, you would typically do this at shutdown (for example, when undeploying your webapp).
	            if (cluster != null)
	                cluster.close();
	        }
	    }
	    
	@PostConstruct
	private void init() { 
		
	}
}
