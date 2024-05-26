package com.uea.team.StoryTelling.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Service.StoryService;
import com.uea.team.StoryTelling.dtos.AddSnippetDTO;
import com.uea.team.StoryTelling.dtos.AddStoryDTO;
import com.uea.team.StoryTelling.models.Snippet;
import com.uea.team.StoryTelling.models.Story;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        System.out.println(new Gson().toJson(entity));
        return this.service.createStory(entity).toString();
    }

    @PostMapping("/addSnippet")
    public String addSnippet(@RequestBody AddSnippetDTO entity) {
        System.out.println(entity);

        return entity.toString();
    }

}
