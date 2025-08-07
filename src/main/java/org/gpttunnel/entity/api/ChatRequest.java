package org.gpttunnel.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.gpttunnel.tunnel.APIRequestData;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRequest implements APIRequestData {

    private String model = "gpt-4o-mini"; //gpt-3.5-turbo | gpt-4o-mini | gpt-4o

    private boolean useWalletBalance = true;

    @SerializedName("max_tokens")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxTokens = 250;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Message> messages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object functions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer temperature = 1;

    @SerializedName("max_completion_tokens")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxCompletionTokens;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Message {

        private String role;
        private String content;

        public Message(Role role, String content) {
            this.role = role.getRole();
            this.content = content;
        }
    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class Content {
//
//        private String type;
//        @JsonInclude(JsonInclude.Include.NON_NULL)
//        private String text;
//        @SerializedName("image_url")
//        @JsonInclude(JsonInclude.Include.NON_NULL)
//        private ImageURL imageURL;
//    }
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    public static class ImageURL {
//        @JsonInclude(JsonInclude.Include.NON_NULL)
//        private String url;
//    }

    @Getter
    public enum Role {
        USER("user"),
        SYSTEM("system");

        private final String role;

        Role(String role) {
            this.role = role;
        }
    }
}