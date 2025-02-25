package com.nandhini.OfflineAI.Service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.nandhini.OfflineAI.Model.OllamaRequ;
import com.nandhini.OfflineAI.Model.OllamaRespo;

@Service
public class OllamaService {
	private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";
	private final RestTemplate restTemplate;

	public OllamaService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String generateResponse(String prompt) {
		try {
			OllamaRequ request = new OllamaRequ("deepseek-r1:latest", prompt, false);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<OllamaRespo> response = restTemplate.exchange(OLLAMA_API_URL, HttpMethod.POST,
					new HttpEntity<>(request, headers), OllamaRespo.class);
			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				return response.getBody().response() != null ? response.getBody().response()
						: "Received empty response from model";
			}
			return "Ollama API returned status: " + response.getStatusCode();
		} catch (RestClientException e) {
			return "Error communicating with Ollama: " + e.getMessage();
		}

	}
}
