## gptunnel-wrapper

### Maven

https://jitpack.io/#megoRU/gptunnel-wrapper

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.megoRU</groupId>
    <artifactId>gptunnel-wrapper</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Examples

### ChatRequest

```java
public class Main {
    public static void main(String[] args) {
        GPTTunnelAPI GPTTunnelAPI = new GPTTunnelAPI.Builder()
                .setToken("token")
                .build();

        ChatRequest gptRequest = new ChatRequest();
//        gptRequest.setModel("gpt-4o");
//        gptRequest.setMaxTokens(1000);

        List<ChatRequest.Content> content = new ArrayList<>();

        ChatRequest.Content gptContent = new ChatRequest.Content("text", "text", null);
        content.add(gptContent);

        ChatRequest.Message userMessage = new ChatRequest.Message(ChatRequest.Role.USER, content);
        userMessage.setContent(content);

        gptRequest.setMessages(List.of(userMessage));

        ChatCompletion chatCompletion = GPTTunnelAPI.chatCompletion(gptRequest);

        String text = chatCompletion.getChoices()[0].getMessage().getContent();
        System.out.println(text);
    }
}
```

### AssistantRequest

```java
public class Main {
    public static void main(String[] args) throws Exception {
      GPTTunnelAPI GPTTunnelAPI = new GPTTunnelAPI.Builder()
                .setToken("token")
                .build();

       AssistantRequest assistantRequest = AssistantRequest.builder()
                .chatId(UUID.randomUUID().toString())
                .assistantCode("ai302312312")
                .useWalletBalance(true)
                .message("Привет")
                .build();

        ChatAssistant chatAssistant = GPTTunnelAPI.chatAssistant(assistantRequest);
        System.out.println(chatAssistant.getMessage());
    }
}
```

## Dependencies

1. [Gson](https://github.com/google/gson)
2. [Apache HttpClient](https://github.com/apache/httpcomponents-client)
3. [JSON-java](https://github.com/stleary/JSON-java)
4. [okhttp](https://github.com/square/okhttp)

## Links

* [Contact me](https://megoru.ru)
