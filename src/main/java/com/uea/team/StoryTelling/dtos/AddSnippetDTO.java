package com.uea.team.StoryTelling.dtos;

public class AddSnippetDTO {
    private int storyId;
    private String snippet;

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}
