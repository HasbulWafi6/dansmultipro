package com.bezkoder.spring.security.postgresql.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  private static final String JOB_API_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

  RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    String result = restTemplate.getForObject(JOB_API_URL, String.class);
    return result;
  }

  @GetMapping("/job/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccessId(@PathVariable String id) {
    String JOB_API_ID_URL = "http://dev3.dansmultipro.co.id/api/recruitment/positions/"+id;
    String result = restTemplate.getForObject(JOB_API_ID_URL, String.class);
    return result;
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
