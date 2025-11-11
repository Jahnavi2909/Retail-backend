package com.yourcompany.yourproject.controller; // 👈 use your actual package name

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String healthCheck() {
        return "✅ Backend is up and running!";
    }
}
