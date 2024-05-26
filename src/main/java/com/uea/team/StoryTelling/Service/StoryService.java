package com.uea.team.StoryTelling.Service;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.uea.team.StoryTelling.database.StoryRepository;
import com.uea.team.StoryTelling.dtos.AddSnippetDTO;
import com.uea.team.StoryTelling.dtos.AddStoryDTO;
import com.uea.team.StoryTelling.models.Story;

@Service
public class StoryService {

    private StoryRepository repository;

    public StoryService() {
        this.repository = new StoryRepository();
    }

    public Story createStory(AddStoryDTO newStory) {

        Story story = new Story(0, newStory.getName());
        if (newStory.getFirstSnippet() != null) {
            story.addSnippet(newStory.getFirstSnippet());
        }

        this.repository.save(story);
        return story;
    }

    public Story addSnippetToStory(AddSnippetDTO newSnippet) {
        Story story = getStoryById(newSnippet.getStoryId());
        story.addSnippet(newSnippet.getSnippet());
        repository.update(story);
        return story;
    }

    public JsonElement getAllStories() {
        return new Gson().toJsonTree(repository.getAllStories());
    }

    public Story getStoryById(int id) {
        return this.repository.geStoryById(id);
    }
}
