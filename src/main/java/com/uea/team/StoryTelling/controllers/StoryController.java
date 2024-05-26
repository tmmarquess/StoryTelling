package com.uea.team.StoryTelling.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Service.StoryService;
import com.uea.team.StoryTelling.dtos.AddSnippetDTO;
import com.uea.team.StoryTelling.dtos.AddStoryDTO;
import com.uea.team.StoryTelling.dtos.EditSnippetDTO;
import com.uea.team.StoryTelling.dtos.EditStoryDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/story")
public class StoryController {

    private StoryService service;
    private Gson json;

    public StoryController(StoryService service) {
        this.service = service;
        json = new Gson();
    }

    @GetMapping("/stories")
    public String getStories() {
        return service.getAllStories().toString();
    }

    @GetMapping("/id/{id}")
    public String getStory(@PathVariable("id") int id) {
        return json.toJson(service.getStoryById(id));
    }

    @PostMapping("/createStory")
    public String createStory(@RequestBody AddStoryDTO entity) {
        return json.toJson(this.service.createStory(entity));
    }

    @PutMapping("/editStory")
    public String editStory(@RequestBody EditStoryDTO entity) {
        return json.toJson(this.service.editStory(entity));
    }

    @DeleteMapping("/deleteStory/{id}")
    public String deleteStory(@PathVariable("id") int id) {
        return json.toJson(this.service.deleteStory(id));
    }

    @PostMapping("/addSnippet")
    public String addSnippet(@RequestBody AddSnippetDTO entity) {
        return json.toJson(this.service.addSnippetToStory(entity));
    }

    @PutMapping("/editSnippet")
    public String editSnippet(@RequestBody EditSnippetDTO entity) {
        return json.toJson(this.service.editSnippet(entity));
    }

    @DeleteMapping("/deleteSnippet/{storyId}-{SnippetId}")
    public String deleteSnippet(@PathVariable("storyId") int storyId, @PathVariable("SnippetId") int snippetId) {
        return json.toJson(this.service.deleteSnippet(storyId, snippetId));
    }

}
