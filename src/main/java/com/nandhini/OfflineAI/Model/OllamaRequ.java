package com.nandhini.OfflineAI.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OllamaRequ(
    String model,
    String prompt,
    boolean stream
) {}
