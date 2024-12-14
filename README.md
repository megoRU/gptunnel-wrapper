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
    <version>1.0.5</version>
</dependency>
```

## Examples

### ChatRequest

```java
public class Main {
    public static void main(String[] args) {
        GPTTunnelAPI gptTunnelAPI = new GPTTunnelAPI.Builder()
                .setToken("token")
                .setDevMode()
                .build();

        ChatRequest gptRequest = new ChatRequest();
        gptRequest.setModel("gpt-4o");
        gptRequest.setMaxTokens(1000);

        ChatRequest.Message message = new ChatRequest.Message(ChatRequest.Role.USER.getRole(), "Hello");
        gptRequest.setMessages(List.of(message));

        ChatCompletion chatCompletion = gptTunnelAPI.chatCompletion(gptRequest);

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
                .setDevMode()
                .build();

        AssistantRequest assistantRequest = AssistantRequest.builder()
                .chatId(UUID.randomUUID().toString())
                .assistantCode("ai302...")
                .useWalletBalance(true)
                .maxContext(16)
                .message("hello")
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
