package com.demo.url_shortner.controller;

import com.demo.url_shortner.service.URLService;
import com.demo.url_shortner.utils.AppResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class URLController {

    private final URLService urlService;

    @GetMapping("/api/shorten-url")
    public ResponseEntity<AppResponse> shortenUrlLink(@RequestParam String longUrl) {
        String response = urlService.shortenUrl(longUrl);
        return ResponseEntity.ok(AppResponse.builder().message("Response").data(response).build());
    }


    @GetMapping("/{shortUrl}")
    public void redirectToLongLink(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {

        String longUrl = urlService.getLongUrl(shortUrl);
        if (longUrl != null) {
            response.sendRedirect(longUrl);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "URL not found");
        }
    }
}
