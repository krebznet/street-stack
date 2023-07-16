package com.dunkware.trade.service.beach.server.controller;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.trade.service.beach.server.runtime.BeachBrokerBean;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin("*")
public class PerformanceController {


    PerformanceController( ) {
        
    }

 
    @GetMapping(path = "/trade/poc/dash/brokers", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<DataGridUpdate> dataGrid(@RequestParam int seconds) {
        return Flux
                .interval(Duration.ofMillis( seconds))
                .map(sequence -> this.buildUpdate());
    }


    static int i = 1;

    public DataGridUpdate buildUpdate() {
        System.out.println("build upddate" + i);
        BeachBrokerBean broker = new BeachBrokerBean();
        broker.setId(i++);
        broker.setName("broker" + broker.getId());
        broker.setStatus("connected");
        DataGridUpdate update = new DataGridUpdate();
        update.setId((int	)broker.getId());
        update.setType("ADD");;
        update.setJson(broker);
        return update;

        // so you should iterate through the fuckin nodes and call update . delete or insert
    }

    public static class Broker  {
        private String name;
        private String status;
        private int id;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
    private class DataGridUpdate {
        private int id;
        private Object json;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

       

        public Object getJson() {
			return json;
		}

		public void setJson(Object json) {
			this.json = json;
		}

		public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
