package com.srg.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapsConfig {

    @Value("${google.maps.api.key}")
    private String apiKey;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public GoogleMapsConfig(String apiKey) {
		super();
		this.apiKey = apiKey;
	}
    
	public GoogleMapsConfig() {
		
	}
    
}
