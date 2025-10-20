package agent.multitool;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.Annotations.Schema;
import com.google.adk.tools.FunctionTool;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class MultiToolAgent {

    static String USER_ID = "student";
    static String NAME = "multi_tool_agent";
    public static final BaseAgent ROOT_AGENT = initAgent();

    public static BaseAgent initAgent() {
        return LlmAgent.builder()
                .name(NAME)
                .model("gemini-2.5-flash")
                .description("Agent to answer questions about the time and weather in a city.")
                .instruction("""
                        You are a helpful assistant that tells the current time and weather in a city.
                        In the end combine the two answers in one.
                        Before calling the method getCurrentTime convert the city to a timezone and then call getCurrentTime
                        Observe the daylight saving is represented as dst in the answer for the getCurrentTime
                        After that get the city lat long and call the getWeather.
                        The answer must have time zone, time in 12-hour format and daylight savings (dst)
                        Add weather jokes to the weather report
                        """)
                .tools(
                        FunctionTool.create(MultiToolAgent.class, "getCurrentTime"),
                        FunctionTool.create(MultiToolAgent.class, "getWeather"))
                .build();
    }

    @Schema(description = "Get the current time for a given city")
    public static Map<String, String> getCurrentTime(
            @Schema(name = "timezone", description = "Time Zone of the city to get the time for") String timezone) {

        String apiUrl = "https://worldtimeapi.org/api/timezone/" + timezone;

        String data = null;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                data = response.body();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return Map.of(
                "timezone", timezone,
                "forecast", data != null ? data : "Timezone not found"
        );
    }

    @Schema(description = "Get the current weather for a given city by calling the method passing latitude and longitude as parameters:")
    public static Map<String, Object> getWeather(
            @Schema(name = "latitude", description = "The latitude of the city") double latitude,
            @Schema(name = "longitude", description = "The longitude of the city") double longitude) {

        System.out.println("getWeather called");
        System.out.println("lat=" + latitude + ", lon=" + longitude);
        String apiUrl = "https://weather.googleapis.com/v1/currentConditions:lookup?key=" + System.getenv("GOOGLE_CLOUD_API_KEY") + "&location.latitude=" + latitude + "&location.longitude=" + longitude;

        String data = null;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                data = response.body();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (data != null) {
            return Map.of("status", "success", "report", data);
        } else {
            return Map.of("status", "error", "report", "Weather information not available");
        }
    }
}