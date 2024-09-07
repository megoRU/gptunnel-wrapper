package org.mego.entity.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mego.impl.APIRequestData;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRequest implements APIRequestData {

    private String model = "gpt-4o-mini"; //gpt-3.5-turbo | gpt-4o-mini | gpt-4o
    private boolean useWalletBalance = true;
    @SerializedName("max_tokens")
    private int maxTokens = 250;
    private List<Message> messages;
    private int temperature = 1;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Message {

        private String role;
        private List<Content> content;

        public Message(Role role, List<Content> content) {
            this.role = role.getRole();
            this.content = content;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Content {

        private String type;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String text;
        @SerializedName("image_url")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private ImageURL imageURL;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ImageURL {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String url;
    }

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