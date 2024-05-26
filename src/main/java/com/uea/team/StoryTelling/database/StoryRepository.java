package com.uea.team.StoryTelling.database;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Blockchain.Blockchain;
import com.uea.team.StoryTelling.Blockchain.Transaction;
import com.uea.team.StoryTelling.models.Story;

public class StoryRepository {

    private Blockchain blockchain;
    private Database database;

    public StoryRepository() {
        this.database = Database.INSTANCE;
        this.blockchain = database.blockchain;
    }

    public Story save(Story newStory) {
        List<Transaction> transactionsList = blockchain.getLatestTransactions();
        newStory.setId(transactionsList.size());
        Transaction t = new Transaction(newStory.getId(), "create", new Gson().toJson(newStory));
        blockchain.addBlock(t);

        database.saveBlockchain();
        return newStory;
    }

    public Story update(Story story) {
        Transaction t = new Transaction(story.getId(), "update", new Gson().toJson(story));
        blockchain.addBlock(t);

        database.saveBlockchain();
        return story;
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

        return stories.get(id);
    }
}
