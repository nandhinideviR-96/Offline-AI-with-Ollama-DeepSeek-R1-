package com.nandhini.OfflineAI.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OllamaRespo(
    String model,
    String response,
    String created_at,
    boolean done
) {}
