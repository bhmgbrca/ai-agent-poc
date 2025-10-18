# AI Agent POC – Java Example Using ADK

This repository contains a proof-of-concept (POC) implementation of a Java agent built with the **Google ADK SDK**, following the ["Get Started" Java tutorial](https://google.github.io/adk-docs/get-started/java/).  
The goal is to explore **agent SDK behaviors**, documentation quality, and integration patterns across multiple frameworks.

---

## 🚀 Getting Started

### Prerequisites

- **Java 21**
- **Maven**

### Installation & Running

1. **Clone this repository**

    ```bash
    git clone https://github.com/bhmgbrca/ai-agent-poc.git
    cd ai-agent-poc
    ```

2. **Create a Google API Key**

    - Visit [Google AI Studio – API Keys](https://aistudio.google.com/app/apikey)
    - Click **“Create API Key”**
    - Copy the generated key — it will be used to authenticate your ADK requests.
    - Keep it secure and **never commit it to Git**.
   

3. **Set your Google API Key**

   Create a `.env` file containing your API key:

    ```bash
    echo 'export GOOGLE_API_KEY="YOUR_API_KEY"' > .env
    ```

   Replace `"YOUR_API_KEY"` with your actual Google API key.  
   This environment variable will be used by the agent to authenticate with Google ADK.

   **Remember to load keys and settings: source .env OR env.bat**


3. **Run the example agent**

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

## 📄 What's Included

- Implementation of the **Google ADK Java "Get Started"** example
- Clean project structure and configuration (`pom.xml`)
- Minor adjustments to align with standard project conventions
- A foundation for exploring:
    - Agent behavior and context management
    - SDK integration approaches
    - Documentation quality comparisons

---

## 🎯 Purpose

This POC aims to:

- Validate the setup and execution of the **Google ADK Java** example
- Provide a foundation for experimentation with other **AI SDKs and agent frameworks**
- Serve as a baseline for evaluating developer experience, SDK API design, and integration ergonomics
- Test different uses of the instructions

---

## 🧾 Attribution & Licensing

This code is adapted from the [Google ADK "Get Started" Java tutorial](https://google.github.io/adk-docs/get-started/java/).

> Some parts of the code and configuration are derived from Google’s official examples for educational and research purposes.

**License:** MIT (recommended — add a `LICENSE` file)  
See the [pull request](https://github.com/bhmgbrca/ai-agent-poc/pull/1) for context and initial implementation.

---

## 🙋‍♂️ Contributing

Contributions are welcome!  
You can:
- Open issues for improvements or questions
- Submit pull requests to add new SDK experiments
- Share insights about SDK behavior or developer experience

---

## ⚠️ Disclaimer

This project is for **educational and proof-of-concept purposes only**.  
It is **not production-ready** and may change as new SDK versions or features are explored.

---

**Author:** https://github.com/bhmgbrca
