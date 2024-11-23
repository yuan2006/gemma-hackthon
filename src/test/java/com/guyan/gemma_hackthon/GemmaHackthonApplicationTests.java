package com.guyan.gemma_hackthon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

class GemmaHackthonApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String json = Files.readString(Path.of("C:\\Users\\52744\\Downloads\\aiwei.json"));

        Gson gson = new Gson();
        List<ConversationNode> myList = gson.fromJson(json, new TypeToken<>() {
        });

        String text = myList.stream().map(node -> {
            return node.getConversation().stream().map(inner -> {
                return "问：" + inner.getInput() + System.lineSeparator() +
                        "答：" + inner.getOutput();
            }).collect(Collectors.joining(System.lineSeparator() + System.lineSeparator()));
        }).collect(Collectors.joining(System.lineSeparator() + System.lineSeparator()));
        System.out.println(text);
        Files.writeString(Path.of("aiwei.txt"), text);
    }


    @Data
    public static class ConversationRoot {
        private List<ConversationNode> nodeList;
    }

    @Data
    public static class ConversationNode {
        private List<Conversation> conversation;
    }

    @Data
    public static class Conversation {
        private String system;
        private String input;
        private String output;
    }

}
