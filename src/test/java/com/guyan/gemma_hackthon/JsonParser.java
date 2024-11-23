package com.guyan.gemma_hackthon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// 会话类
class Conversation {
    private String system;
    private String input;
    private String output;

    // getter 和 setter 方法
    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

// 节点类
class Node {
    private List<Conversation> conversation;

    // getter 和 setter 方法
    public List<Conversation> getConversation() {
        return conversation;
    }

    public void setConversation(List<Conversation> conversation) {
        this.conversation = conversation;
    }
}

// 根节点类，用于匹配 JSON 数组
class ConversationRoot {
    private List<Node> nodeList;

    // getter 和 setter 方法
    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}

public class JsonParser {
    public static void main(String[] args) {
        try {
            String json = Files.readString(Path.of("C:\\Users\\52744\\Downloads\\aiwei.json"));
            ObjectMapper objectMapper = new ObjectMapper();

            // 使用 TypeReference 来指定泛型类型
            ConversationRoot conversationRoot = objectMapper.readValue(json, new TypeReference<ConversationRoot>() {});

            // 处理 nodeList
            for (Node node : conversationRoot.getNodeList()) {
                for (Conversation conversation : node.getConversation()) {
                    System.out.println("System: " + conversation.getSystem());
                    System.out.println("Input: " + conversation.getInput());
                    System.out.println("Output: " + conversation.getOutput());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}