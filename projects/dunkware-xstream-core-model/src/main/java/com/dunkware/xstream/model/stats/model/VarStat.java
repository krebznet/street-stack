package com.dunkware.xstream.model.stats.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.dunkware.common.util.time.DunkTime;

public class VarStat {

	private long id = -1;
	private int entity;
	private LocalDate date;
	private int type;
	private Number value;
	private LocalTime time;
	private int var;

	public int getEntity() {
		return entity;
	}

	public void setEntity(int entity) {
		this.entity = entity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVar() {
		return var;
	}

	public void setVar(int var) {
		this.var = var;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(":");
		builder.append(DunkTime.format(date, DunkTime.YYYY_MM_DD));
		builder.append(":");
		return builder.append(entity).append(":").append(type).append(":").append(value).append(":")
				.append(DunkTime.formatHHMMSS(time)).toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VarStat) {
			VarStat compare = (VarStat) obj;
			if (compare.getDate().isAfter(date) == false && compare.getDate().isBefore(date) == false) {
				if (time.equals(compare.getTime())) {
					if (type == compare.getType()) {
						if (var == compare.getVar()) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}

}
