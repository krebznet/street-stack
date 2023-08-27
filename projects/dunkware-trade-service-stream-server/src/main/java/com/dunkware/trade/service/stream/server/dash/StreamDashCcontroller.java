package com.dunkware.trade.service.stream.server.dash;

import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.common.util.datagrid.DataGridUpdate;
import com.dunkware.common.util.glazed.GlazedDataGrid;
import com.dunkware.trade.service.stream.server.controller.StreamControllerService;

import reactor.core.publisher.Flux;

@RestController
public class StreamDashCcontroller {

	@Autowired
	private StreamControllerService streamService;

	@GetMapping(path = "/stream/v1/dash/entity/scanner", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<List<DataGridUpdate>> entityScanner(@RequestBody() Object webscanner, @RequestParam() String stream) {
		GlazedDataGrid grid = null; //GlazedDataGrid.newInstance(beachService.getBrokerBeans(), runtime.getExecutor(), "getId");
		grid.start();
		Flux<List<DataGridUpdate>> results = grid.getUpdates();
		// results = results.subscribeOn(Schedulers.boundedElastic());
		results.subscribe(new Subscriber<List<DataGridUpdate>>() {
			private Subscription sub;
			@Override
			public void onSubscribe(Subscription s) {
				this.sub = s;
				sub.request(1);
			}

			@Override
			public void onNext(List<DataGridUpdate> t) {
				try {

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				sub.request(1);
			}

			@Override
			public void onError(Throwable t) {
				sub.cancel();
				// list.dispose();
			}

			@Override
			public void onComplete() {
				sub.cancel();
			}
		});
		return results;

	}

}
