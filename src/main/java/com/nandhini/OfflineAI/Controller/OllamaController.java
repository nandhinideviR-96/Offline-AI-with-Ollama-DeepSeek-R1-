package com.nandhini.OfflineAI.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nandhini.OfflineAI.Service.OllamaService;

@RestController
@RequestMapping("/api")
public class OllamaController {
	
	private final OllamaService ollamaService;

	public OllamaController(OllamaService ollamaService) {
		this.ollamaService = ollamaService;
	}

	@PostMapping("/generate")
	public ResponseEntity<String> generate(@RequestBody String prompt) {
		if (prompt == null || prompt.isBlank()) {
			return ResponseEntity.badRequest().body("Prompt cannot be empty");
		}
		String response = ollamaService.generateResponse(prompt);
		return ResponseEntity.ok(response);
	}
}
