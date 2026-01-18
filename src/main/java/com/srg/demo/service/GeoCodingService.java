package com.srg.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeoCodingService {

    @Value("${google.maps.api.key}")   // ← HERE
    private String apiKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public GeoCodingService(String apiKey) {
		super();
		this.apiKey = apiKey;
	}
    
	public GeoCodingService() {
		
	}
}
