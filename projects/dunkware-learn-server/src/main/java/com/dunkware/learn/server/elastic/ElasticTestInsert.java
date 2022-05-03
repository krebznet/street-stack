package com.dunkware.learn.server.elastic;

import javax.annotation.PostConstruct;
import javax.swing.text.DefaultEditorKit.InsertTabAction;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;


@Service
public class ElasticTestInsert {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static void main(String[] args) {
		ElasticTestInsert insert = new ElasticTestInsert();
		insert.test();
	}
	
	@PostConstruct
	public void test() { 
		try {
		RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("172.16.16.55", 31098, null)
                ));
		
		CreateIndexRequest bulkRequest = new CreateIndexRequest("street-signals");
		 bulkRequest.mapping(
	                "{\n" +
	                        "  \"vars\": {\n" +
	                        "    \"row\": {\n" +
	                        "      \"type\": \"text\"\n" +
	                        "       \"norms\": \"false\"\n" +
	                        "    }\n" +
	                        "  }\n" +
	                        "}", XContentType.JSON);
		 try {
			CreateIndexResponse resp = client.indices().create(bulkRequest, RequestOptions.DEFAULT);	
			System.out.println("Ack " + resp.isAcknowledged());
			System.out.println();
		 } catch (Exception e) {
			 e.printStackTrace();
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
