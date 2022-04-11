package com.dunkware.trade.service.web.server.chart;

import com.dunkware.net.proto.chart.ChartRequest;
import com.dunkware.net.proto.chart.ChartResponse;
import com.dunkware.net.proto.chart.ChartServiceGrpc.ChartServiceImplBase;
import com.dunkware.net.proto.chart.Grid;
import com.dunkware.net.proto.chart.GridRequest;
import com.dunkware.net.proto.chart.GridSubscriptionRequest;
import com.dunkware.net.proto.chart.GridTransaction;

import io.grpc.stub.StreamObserver;

public class GenesisChartService extends ChartServiceImplBase {
	
	

	@Override
	public void getChart(ChartRequest request, StreamObserver<ChartResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.getChart(request, responseObserver);
	}

	@Override
	public void getGrid(GridRequest request, StreamObserver<Grid> responseObserver) {
		// TODO Auto-generated method stub
		super.getGrid(request, responseObserver);
	}

	@Override
	public void getGridSubscription(GridSubscriptionRequest request, StreamObserver<GridTransaction> responseObserver) {
		// TODO Auto-generated method stub
		
		super.getGridSubscription(request, responseObserver);
	}

	
	

}
