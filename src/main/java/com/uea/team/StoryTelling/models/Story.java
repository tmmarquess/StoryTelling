package com.uea.team.StoryTelling.models;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private int id;

    private String name;

    private List<Snippet> snippets;

    private boolean isDeleted;

    public Story(int id, String name) {
        this.id = id;
        this.name = name;
        this.snippets = new ArrayList<>();
        isDeleted = false;
    }

    public Story(int id, String name, List<Snippet> snippets) {
        this.id = id;
        this.name = name;
        this.snippets = snippets;
        isDeleted = false;
    }

    public Story() {
        this.snippets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }

    public void addSnippet(String text) {
        Snippet snippet = new Snippet(this.snippets.size(), text);
        this.snippets.add(snippet);
    }

    public String getStory() {
        String fullStory = "";
        for (Snippet s : this.snippets) {
            fullStory += s;
        }
        return fullStory;
    }

    @Override
    public String toString() {
        return "Story [id=" + id + ", name=" + name + ", story=" + getStory() + ", deleted=" + isDeleted + "]";
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
