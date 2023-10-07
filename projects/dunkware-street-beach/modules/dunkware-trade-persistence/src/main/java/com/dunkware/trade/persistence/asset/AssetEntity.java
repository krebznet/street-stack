package com.dunkware.trade.persistence.asset;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity(name = "AssetEntity")
@Table(name = "trade_asset")
public class AssetEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String json;
    private LocalDateTime created; 
    
    public AssetEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
    
    

  
}
