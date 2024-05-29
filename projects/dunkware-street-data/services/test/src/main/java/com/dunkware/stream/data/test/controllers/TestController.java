package com.dunkware.stream.data.test.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionKey;
import com.dunkware.stream.data.cassy.entity.sesion.StreamSessionRow;
import com.dunkware.stream.data.cassy.entity.stats.EntityStatsKey;
import com.dunkware.stream.data.cassy.entity.stats.EntityStatsRow;
import com.dunkware.stream.data.cassy.entity.stats.EntityVarStats;
import com.dunkware.stream.data.cassy.repository.session.StreamSessionRepo;
import com.dunkware.stream.data.cassy.repository.stats.EntityStatsRepo;

@RestController
public class TestController {

	private final EntityStatsRepo entityStatsRepo;

	private final StreamSessionRepo sessionRepo;

	public TestController(EntityStatsRepo statRepo, StreamSessionRepo sessionRepo) {
		this.entityStatsRepo = statRepo;
		this.sessionRepo = sessionRepo;

	}

	@GetMapping(path = "/insert/session")
	public String insertSessionMeta() {
		StreamSessionKey key = new StreamSessionKey(0, LocalDate.now().minusDays(2));
		StreamSessionRow ent = new StreamSessionRow();
		ent.setKey(key);
		List<Integer> ents = new ArrayList<Integer>();
		int i = 0;
		while (i < 8000) {
			ents.add(1);
			i++;
		}
		ent.setEntities(ents);
		ent.setSignals(Arrays.asList(34, 34, 34, 34, 34, 3));
		ent.setVars(Arrays.asList(23, 23, 23, 23, 23));
		ent.setStart(LocalTime.of(9, 30, 0));
		ent.setStop(LocalTime.of(13, 29, 30));
		ent.setStats(Arrays.asList(3, 32, 3));
		try {
			sessionRepo.save(ent);
			return "saved";
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString(); //
		}

	}

	@GetMapping(path = "/insert/entity_stats")
	public void testInsert() {
		EntityStatsRow ent = new EntityStatsRow();
		EntityStatsKey key = new EntityStatsKey();
		key.setDate(LocalDate.of(2024, 2, 15));
		key.setEntity(2);
		key.setStream(1);
		ent.setKey(key);
		ent.getSignals().put(1, 100);
		EntityVarStats var = new EntityVarStats();

		var.getStats().put(1, 23.4);
		var.getStats().put(2, 32.3);
		var.getStats().put(3, 32.3);
		var.getStats().put(4, 32.3);

		var.getStats().put(5, 32.3);
		var.getStats().put(6, 32.3);

		var.getTimes().put(1, LocalTime.of(3, 3, 3));

		ent.getVars().put(1, var);
		var = new EntityVarStats();

		var.getStats().put(1, 23.4);
		var.getStats().put(2, 32.3);
		var.getStats().put(3, 32.3);
		var.getStats().put(4, 32.3);

		var.getStats().put(5, 32.3);
		var.getStats().put(6, 32.3);

		var.getTimes().put(2, LocalTime.of(3, 3, 3));

		ent.getVars().put(2, var);

		try {
			entityStatsRepo.save(ent);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
