# gptunnel-wrapper

Java wrapper for interacting with GPT services via GPTunnel.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Maven

Add via [JitPack](https://jitpack.io/#megoRU/gptunnel-wrapper):

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
    <version>1.1.1</version>
</dependency>
```

## Examples

### Chat Request

```java
public class Main {
    public static void main(String[] args) {
        GPTTunnelAPI gptTunnelAPI = new GPTTunnelAPI.Builder()
                .setToken("your-token")
                .setDevMode()
                .build();

        ChatRequest gptRequest = new ChatRequest();
        gptRequest.setModel("gpt-4o");
        gptRequest.setMaxTokens(1000);
        gptRequest.setMessages(List.of(
            new ChatRequest.Message(ChatRequest.Role.USER.getRole(), "Hello")
        ));

        ChatCompletion chatCompletion = gptTunnelAPI.chatCompletion(gptRequest);
        System.out.println(chatCompletion.getChoices()[0].getMessage().getContent());
    }
}
```

### Assistant Request

```java
public class Main {
    public static void main(String[] args) {
        GPTTunnelAPI gptTunnelAPI = new GPTTunnelAPI.Builder()
                .setToken("your-token")
                .setDevMode()
                .build();

        AssistantRequest assistantRequest = AssistantRequest.builder()
                .chatId(UUID.randomUUID().toString())
                .assistantCode("ai302...")
                .useWalletBalance(true)
                .maxContext(16)
                .message("hello")
                .build();

        ChatAssistant chatAssistant = gptTunnelAPI.chatAssistant(assistantRequest);
        System.out.println(chatAssistant.getMessage());
    }
}
```

## Dependencies

* [Gson](https://github.com/google/gson)
* [Apache HttpClient](https://github.com/apache/httpcomponents-client)
* [JSON-java](https://github.com/stleary/JSON-java)
* [OkHttp](https://github.com/square/okhttp)

## Links

* [Contact me](https://megoru.ru)

## License

Licensed under the [MIT License](https://opensource.org/licenses/MIT).
