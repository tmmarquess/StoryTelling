package com.uea.team.StoryTelling.dtos;

public class EditSnippetDTO {
    private int storyId;
    private int snippetId;
    private String snippet;

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getSnippetId() {
        return snippetId;
    }

    public void setSnippetId(int snippetId) {
        this.snippetId = snippetId;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}
