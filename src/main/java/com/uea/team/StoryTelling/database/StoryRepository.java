package com.uea.team.StoryTelling.database;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Blockchain.Blockchain;
import com.uea.team.StoryTelling.Blockchain.Transaction;
import com.uea.team.StoryTelling.models.Story;

public class StoryRepository {

    private Blockchain blockchain;

    public StoryRepository() {
        this.blockchain = new Blockchain(3);
    }

    public Story save(Story newStory) {
        List<Transaction> transactionsList = blockchain.getLatestTransactions();
        newStory.setId(transactionsList.size());
        Transaction t = new Transaction(newStory.getId(), "create", new Gson().toJson(newStory));
        blockchain.addBlock(t);
        return newStory;
    }

    public ArrayList<Story> getAllStories() {
        ArrayList<Story> stories = new ArrayList<>();
        List<Transaction> transactionsList = blockchain.getLatestTransactions();

        for (Transaction t : transactionsList) {
            stories.add(new Gson().fromJson(t.getData(), Story.class));
        }

        return stories;
    }

    public Story geStoryById(int id) {
        ArrayList<Story> stories = getAllStories();
        System.out.println("=========================");
        System.out.println(id == stories.get(id).getId());
        System.out.println("=========================");

        return stories.get(id);
    }
}
