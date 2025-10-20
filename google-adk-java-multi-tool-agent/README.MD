# AI Agent POC – ADK Java Multi-Tool Agent

This project is an extension of the [Google ADK Java example](../google-adk-java), demonstrating how to create a **multi-tool AI agent** in Java using the **Google AI Developer Kit (ADK)**.

The agent uses multiple function tools — `getCurrentTime` and `getWeather` — to answer questions about **time** and **weather** in any city.  
It showcases how to define, register, and invoke multiple tools from a single agent.

---

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven** (build tool)
- A valid **Google API Key** from [Google AI Studio](https://aistudio.google.com/app/apikey)

---

### Installation & Running

1. **Clone this repository**

    ```bash
    git clone https://github.com/bhmgbrca/ai-agent-poc.git
    cd ai-agent-poc/google-adk-java-multi-tool-agent
    ```

2. **Create a Google API Key**

    - Visit [Google AI Studio – API Keys](https://aistudio.google.com/app/apikey)
    - Click **“Create API Key”**
    - Copy the generated key — it will be used to authenticate your ADK requests.
   - Keep it secure and **never commit it to Git**.
   
3. **Set your Google API Key**

    ```bash
    export GOOGLE_API_KEY="YOUR_API_KEY"
    ```

4. **Run the example**

    ```bash
    mvn compile exec:java -Dexec.mainClass="com.example.agent.AgentCliRunner"
    ```
   or

    ```bash
    mvn compile exec:java \
    -Dexec.mainClass="com.google.adk.web.AdkWebServer" \
    -Dexec.args="--adk.agents.source-dir=target --server.port=8000"
    ```

---

## 🧩 What’s Included

- Example implementation of a **multi-tool agent** using Google ADK
- Two tools registered:
    - `getCurrentTime` — returns current time for a given timezone
    - `getWeather` — returns mock weather data based on latitude and longitude
- Integration pattern for extending agents with more function tools
- Instruction prompt guiding the LLM to use both tools in sequence

---

## 💡 Example Prompt
>What is the current time and weather in Paris?

Example response:
>In Paris, France, the current time is 11:37 PM (23:37) on Monday, October 20, 2025, and Daylight Saving is active. 
>The weather is partly cloudy with a temperature of 14.8°C, and it feels like 13°C. 
>The wind is blowing from the southwest at 21 km/h with gusts up to 37 km/h. There's a 5% chance of rain. 
>Why did the cloud break up with the fog? Because he was too clingy!

---

## 🧭 Purpose

This POC demonstrates how to:

- Combine multiple tools in a single agent workflow
- Pass data between tools (e.g., timezone → weather lookup)
- Explore ADK reflection-based method invocation
- Experiment with the Gemini model in a Java environment

---

## 🧾 Attribution & Licensing

This example is adapted from Google’s [ADK “Get Started” Java tutorial](https://google.github.io/adk-docs/get-started/quickstart/).  
It extends the base example to illustrate multi-tool interaction.

> Some parts of the code and configuration are derived from Google’s official examples for educational and research purposes.


**License:** MIT (recommended — add a `LICENSE` file)

---

## 🙋‍♂️ Contributing

Contributions are welcome!  
Feel free to open issues or pull requests to:

- Add new tools
- Integrate real weather/time APIs
- Improve prompt engineering and response formatting

---

## ⚠️ Disclaimer

This project is for **educational and proof-of-concept purposes only**.  
It is **not production-ready** and may change as new SDK versions or features are explored.
API usage, error handling, and response parsing are simplified for demonstration.

---

**Author:** https://github.com/bhmgbrca

