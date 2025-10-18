package com.example.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HelloTimeAgent {

    public static BaseAgent ROOT_AGENT = initAgent();

    private static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name("hello-time-agent")
                .description("Tells the current time in a specified city")
                .instruction("""
                You are a helpful assistant that tells the current time in a city.
                Before calling the  method getCurrentTime convert the city to a timezone and then call getCurrentTime
                After calling the method build an answer that contains the time zone, time in dd/MM/yyyy hh:mm:ss and check if the city is on daylight savings.
                """)
                .model("gemini-2.5-flash")
                .tools(FunctionTool.create(HelloTimeAgent.class, "getCurrentTime"))
                .build();
    }

    @Schema(description = "Get the current time for a given city")
    public static Map<String, String> getCurrentTime(
            @Schema(name = "timezone", description = "Time Zone of the city to get the time for") String timezone) {

        String apiUrl = "https://worldtimeapi.org/api/timezone/" + timezone;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        WorldTime data = null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Parse JSON response
                ObjectMapper mapper = new ObjectMapper();
                data = mapper.readValue(response.body(), WorldTime.class);
                System.out.println("response: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return Map.of(
                "timezone", timezone,
                "forecast", data != null ? String.format("The time is: %s, Daylight Savings", data.datetime(), data.dst()) : "Timezone not found"
        );
    }
}