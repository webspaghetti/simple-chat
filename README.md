# Simple Chat

An extremely simple client-server chat application developed as learning exercise built with Java 17 and JavaFX.

## Prerequisites

* **Java 17** or higher
* **Maven**

## Build

From the root directory:

```bash
mvn clean install
```

## How to Run

### 1. Start the Server
The server listens on port `5000` by default.

```bash
cd simpleChat-server
mvn compile exec:java "-Dexec.mainClass=xyz.webspaghetti.SimpleChatServer"
```

### 2. Start the Client
Open a new terminal for each client you want to launch.

```bash
cd simpleChat-client
mvn javafx:run
```

## Usage

1.  Launch the Client application.
2.  Enter a **Username**.
3.  Enter the **Hostname** (use `localhost` for local testing).
4.  Enter **Port** `5000`.
5.  Click **Connect** and start chatting.
