package com.uea.team.StoryTelling.models;

public class Snippet {
    private int id;

    private String content;

    public Snippet(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Snippet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Snippet [id=" + id + ", content=" + content + "]";
    }

}
