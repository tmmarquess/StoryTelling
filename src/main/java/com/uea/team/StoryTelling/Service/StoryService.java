package com.uea.team.StoryTelling.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.uea.team.StoryTelling.Blockchain.BlockchainNotValidException;
import com.uea.team.StoryTelling.database.StoryRepository;
import com.uea.team.StoryTelling.dtos.AddSnippetDTO;
import com.uea.team.StoryTelling.dtos.AddStoryDTO;
import com.uea.team.StoryTelling.dtos.EditSnippetDTO;
import com.uea.team.StoryTelling.dtos.EditStoryDTO;
import com.uea.team.StoryTelling.models.Snippet;
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

        try {
            this.repository.save(story);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
        return story;
    }

    public Story addSnippetToStory(AddSnippetDTO newSnippet) {
        Story story = getStoryById(newSnippet.getStoryId());
        story.addSnippet(newSnippet.getSnippet());
        try {
            repository.update(story);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
        return story;
    }

    public JsonElement getAllStories() {
        try {
            return new Gson().toJsonTree(repository.getAllStories());
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
    }

    public Story getStoryById(int id) {
        try {
            return this.repository.geStoryById(id);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "storyId not found");
        }
    }

    public Snippet editSnippet(EditSnippetDTO snippetDTO) {
        Story story = getStoryById(snippetDTO.getStoryId());
        Snippet snippet = story.getSnippets().get(snippetDTO.getSnippetId());
        snippet.setContent(snippetDTO.getSnippet());
        try {
            repository.update(story);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
        return snippet;
    }

    public Story editStory(EditStoryDTO storyDTO) {
        Story story = getStoryById(storyDTO.getStoryId());
        story.setName(storyDTO.getName());
        try {
            repository.update(story);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
        return story;
    }

    public boolean deleteSnippet(int storyId, int snippetId) {
        Story story = getStoryById(storyId);
        if (story != null) {
            List<Snippet> snippets = story.getSnippets();
            snippets.remove(snippetId);
            try {
                this.repository.update(story);
            } catch (BlockchainNotValidException e) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
            }
            return true;
        }
        return false;

    }

    public boolean deleteStory(int storyId) {
        try {
            Story story = getStoryById(storyId);
            return this.repository.delete(story);
        } catch (BlockchainNotValidException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid Blockchain");
        }
    }
}
