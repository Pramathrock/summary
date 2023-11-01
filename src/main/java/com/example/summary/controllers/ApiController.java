package com.example.summary.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ApiController {
	
	

	@PostMapping("/send-data")
	public RedirectView sendDataToExternalWebsite(@RequestParam("summary") String summary) throws IOException, InterruptedException {
		
           String url = "https://mgtechsoft.com/life-at-mgen/";

	       Map<String, String> data = new HashMap<>();

	       data.put("summary", summary);

	       HttpClient httpClient = HttpClient.newHttpClient();

	       HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("https://mgtechsoft.com/ ", "application/json")
	    		   .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(data))).build();

	       HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	       if (response.statusCode() == HttpStatus.OK.value()) {
	    	   System.out.println(response);
	    	   return new RedirectView(url);
	       } 
	       else {
		      return null;
		   }
	  }
}
