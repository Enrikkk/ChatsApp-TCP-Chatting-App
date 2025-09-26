Perfect! Here's a polished, GitHub-ready `README.md` with badges, collapsible sections, tables, and better formatting. It will look professional and clean for your repo.

````markdown
# 🎨 ChatsApp - TCP Chatting Application

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![JavaFX](https://img.shields.io/badge/JavaFX-25-blueviolet.svg)](https://openjfx.io/)

**ChatsApp** is a Java-based TCP chat application developed as part of the **CSCI 4311 course** at the **University of New Orleans**. It allows multiple users to communicate over a network in real-time using a modern **JavaFX GUI**.

---

## 🚀 Features

- **Client-Server Architecture:** Supports multiple clients connecting to a central server.
- **Styled Chat UI:**  
  - **Blue**: Your messages (right-aligned)  
  - **Green**: Other users' messages (left-aligned)  
  - **Grey**: Server messages (centered)
- **Auto-Scroll:** Chat scrolls automatically to show the latest messages.
- **Dynamic Message Expansion:** Long messages wrap and expand the chat area without overlapping.
- **Intro Messages:** Guide users on the meaning of different message types.
- **Multi-Threaded:** Sending and receiving messages happens in separate threads, preventing UI freezes.
- **Graceful Shutdown:** Proper handling of window closes, server shutdowns, and client exits.

---

## 📋 Requirements

- **JDK**: 11 or higher (tested with 23)  
- **JavaFX SDK**: 25  
- **IDE (Optional)**: Eclipse, IntelliJ IDEA, NetBeans, or any Java IDE supporting JavaFX  

---

## ⚙️ Installation

1. Clone the repository:

```bash
git clone https://github.com/Enrikkk/ChatsApp-TCP-Chatting-App.git
cd ChatsApp-TCP-Chatting-App
````

2. Download and extract the JavaFX SDK if you haven’t already.

---

## 💻 Running the Application

### Running in an IDE

<details>
<summary>Click to expand instructions for Eclipse / IntelliJ / NetBeans</summary>

1. Import the project into your IDE.
2. Set `application.Menu` as the **main class**.
3. Configure the **VM arguments** to include JavaFX:

```text
--module-path /path/to/javafx-sdk-25/lib --add-modules javafx.controls,javafx.fxml
```

4. Run the project. The chat UI should appear.

</details>

### Running from Terminal

<details>
<summary>Click to expand terminal instructions</summary>

1. Compile all Java files:

```bash
javac -d out --module-path /path/to/javafx-sdk-25/lib --add-modules javafx.controls,javafx.fxml $(find . -name "*.java")
```

2. Run the application:

```bash
java --module-path /path/to/javafx-sdk-25/lib --add-modules javafx.controls,javafx.fxml -cp out application.Menu
```

</details>

---

## 💬 Message Types & Styling

| Message Type        | Color | Alignment |
| ------------------- | ----- | --------- |
| **Your messages**   | Blue  | Right     |
| **Other users**     | Green | Left      |
| **Server messages** | Grey  | Center    |

* Messages automatically wrap and expand vertically.
* Intro messages appear when the app starts to explain the interface.

---


---

## 📜 License

This project was created for educational purposes as part of **CSCI 4311** at the **University of New Orleans**.

---

## 📝 Notes from Development

* **Threads & Concurrency:**

  * Sending and receiving messages use separate threads for responsive UI.
  * Proper handling of client exit and server shutdowns ensures threads terminate safely.

* **UI Styling:**

  * JavaFX `Label` and `HBox` used to create message bubbles with rounded corners.
  * Auto-scroll implemented to always show the newest messages.

* **Cross-IDE Compatibility:**

  * Works on Eclipse, IntelliJ, NetBeans, and via command line.
  * Ensure correct `module-path` for JavaFX SDK.

---

