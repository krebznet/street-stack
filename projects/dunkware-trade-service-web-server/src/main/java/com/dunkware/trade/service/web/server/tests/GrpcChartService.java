package com.dunkware.trade.service.web.server.tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.dunkware.net.chart.grid.builder.GridBuilder;
import com.dunkware.net.chart.grid.builder.GridBuilderUtil;
import com.dunkware.net.chart.grid.builder.model.GridModel;
import com.dunkware.net.chart.grid.builder.model.GridModelColumnStyleRuleOperator;
import com.dunkware.net.chart.grid.builder.model.GridModelValueParser;
import com.dunkware.net.proto.chart.ChartInput;
import com.dunkware.net.proto.chart.ChartRequest;
import com.dunkware.net.proto.chart.ChartResponse;
import com.dunkware.net.proto.chart.ChartServiceGrpc.ChartServiceImplBase;
import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.chart.GridRequest;
import com.dunkware.net.proto.chart.GridSubscriptionRequest;
import com.dunkware.net.proto.chart.GridTransaction;
import com.dunkware.net.proto.chart.GridTransactionDelete;
import com.dunkware.net.proto.chart.GridTransactionInsert;
import com.dunkware.net.proto.chart.GridTransactionUpdate;
import com.dunkware.net.util.JsonHelper;

import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(ChartServiceImplBase.class)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrpcChartService extends ChartServiceImplBase {
	
	private double lastPrice = 1.0; 
	private LinkedList<Integer> rows = new LinkedList<>();
	private AtomicInteger nextRowId = new AtomicInteger(1);
	
	private int deleteCount = 0;

	@Override
	public void getChart(ChartRequest request, StreamObserver<ChartResponse> responseObserver) {
		int sendCount = 10000;
		int x = 0;
		
		while(x < sendCount) {
			List<Integer> data = new ArrayList<>();
			data.add((int) (Math.random()*10000));
			ChartInput input = ChartInput.newBuilder().setChartConfig("test").setData(data.toString()).build();
			responseObserver.onNext(ChartResponse.newBuilder().setChartInput(input).build());
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			x++;
		}
		responseObserver.onCompleted();

	}

	@Override
	public void getGrid(GridRequest request, StreamObserver<Grid> responseObserver) {
		Grid grid = null; 
		GridModel gridModel = null;
		try {
			// higher level abstract model for building a Grid defined in chart.proto
			GridBuilder modelBuilder = GridBuilder.newBuilder();
			modelBuilder.setId(1);
			modelBuilder.setPivot(false);
			modelBuilder.setStreaming(false);
			modelBuilder.setRowNodeId("id");
			modelBuilder.addColumn("id").setValueParser(GridModelValueParser.numberParser).setHeaderName("ID").add();
			modelBuilder.addColumn("symbol").setHeaderName("Symbol").add();
			modelBuilder.addColumn("price").setHeaderName("Price").addCellClassRule()
			.doubleExpression("rag-red",GridModelColumnStyleRuleOperator.GreaterThan,0.0).add().add();
			modelBuilder.setId(1);
			gridModel = modelBuilder.build();
			StringBuilder dataBuilder = new StringBuilder();
			dataBuilder.append("[");
			MockModel model = new MockModel();
			model.setId(1);
			model.setSymbol("GOOG");
			model.setPrice(lastPrice);
			dataBuilder.append(JsonHelper.serialize(model));
			dataBuilder.append("]");
			//System.out.println(dataBuilder.toString());;
			modelBuilder.setData(dataBuilder.toString());
			
			grid = GridBuilderUtil.modelToGrid(gridModel);
			// okay we now have data set 
			responseObserver.onNext(grid);
			
			responseObserver.onCompleted();
		} catch (Exception e) {
			responseObserver.onError(e);
			return;
		}
		
		
		
		super.getGrid(request, responseObserver);
	}

	@Override
	public void getGridSubscription(GridSubscriptionRequest request, StreamObserver<GridTransaction> responseObserver) {
		// okay here we will loop forever every 1 second and change price 
		// and create a transaction only with row updates 
		while(true) { 
			try {
				Thread.sleep(1000);
				double price = lastPrice + 0.50;
				MockModel model = new MockModel();
				model.setId(1);
				model.setPrice(price);
				model.setSymbol("GOOG");
				GridTransaction.Builder transactionBuilder = GridTransaction.newBuilder();
				GridTransactionUpdate.Builder updateBuilder = GridTransactionUpdate.newBuilder();
				//System.out.println(JsonHelper.serialize(model));
				updateBuilder.setData(JsonHelper.serialize(model));
				lastPrice = price;
				transactionBuilder.addUpdates(updateBuilder.build());
				GridTransactionInsert.Builder insertBuilder = GridTransactionInsert.newBuilder();
				MockModel insert = new MockModel();
				insert.setId(nextRowId.incrementAndGet());
				rows.add(insert.getId());
				insert.setSymbol("GOOG" + nextRowId.get());
				insert.setPrice(44.3 + nextRowId.get());
				insertBuilder.setData(JsonHelper.serialize(insert));
				transactionBuilder.addInserts(insertBuilder.build());
				deleteCount++;
				if(deleteCount == 3) { 
					int removeId = rows.pop();
					MockModel delete = new MockModel();
					delete.setId(removeId);
					
					transactionBuilder.addDeletes(GridTransactionDelete.newBuilder().setData(JsonHelper.serialize(delete)).build());
					deleteCount = 0;	
				}
				GridTransaction transaction = transactionBuilder.build();
				responseObserver.onNext(transaction);
			} catch (Exception e) {
				responseObserver.onError(e);
				return;
			}
		}
	}
	
	
	
	

	/*
	 * @Override public void getGrid(GridRequest request,
	 * StreamObserver<GridResponse> responseObserver) { // Use Our Grid Builder to
	 * create the Grid which has column defenitions and data
	 * 
	 * try { GridModel grid = GridBuilder.newBuilder().addColumn("symbol").add()
	 * .addColumn("price").setValueParser(GridColumnParser.numberParser).add().
	 * setPivot(true).build();
	 * 
	 * String testData = "[\n" + "	{\n" + "		\"symbol\": \"goog\",\n" +
	 * "		\"price\": 32.34\n" + "	},\n" + "	{\n" +
	 * "		\"symbol\": \"tsla\",\n" + "		\"price\": 332.34\n" +
	 * "	},\n" + "	{\n" + "		\"symbol\": \"facebook\",\n" +
	 * "		\"price\": 1.32\n" + "	}\n" + "]"; grid.setData(testData); GridJson
	 * gridJson = GridUtil.modelToJson(grid); String columnDefs =
	 * JsonHelper.serialize(grid.getColumns());
	 * 
	 * Grid grpcGrid =
	 * com.dunkware.angular.service.proto.Grid.newBuilder().setColumns(columnDefs).
	 * setPivot(grid.isPivot()).setData(testData).build();
	 * 
	 * responseObserver.onNext(GridResponse.newBuilder().setGrid(grpcGrid).build());
	 * responseObserver.onCompleted();
	 * 
	 * 
	 * 
	 * } catch (Exception e) { responseObserver.onError(e); return; }
	 * 
	 * 
	 * }
	 * 
	 */



}
