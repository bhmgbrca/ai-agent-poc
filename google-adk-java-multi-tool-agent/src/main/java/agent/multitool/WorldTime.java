package agent.multitool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Define a record to match the API response fields
@JsonIgnoreProperties(ignoreUnknown = true)
record WorldTime(
        String datetime,
        boolean dst
){}