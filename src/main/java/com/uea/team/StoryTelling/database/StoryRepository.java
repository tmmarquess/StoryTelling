package com.uea.team.StoryTelling.database;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Blockchain.Blockchain;
import com.uea.team.StoryTelling.Blockchain.Transaction;
import com.uea.team.StoryTelling.models.Story;

public class StoryRepository {

    private Blockchain blockchain;
    private String blockchainFileName = "./database.blkc";

    public StoryRepository() {
        loadBlockchain();
    }

    private void loadBlockchain() {
        try {
            ObjectInputStream objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(blockchainFileName)));
            Blockchain bl = (Blockchain) objectIn.readObject();
            objectIn.close();
            this.blockchain = bl;
        } catch (IOException exception) {
            this.blockchain = new Blockchain(3);
        } catch (ClassNotFoundException e) {
            System.out.println("uerrrr");
        }
    }

    private void saveBlockchain() {
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(blockchainFileName)));
            objectOut.writeObject(this.blockchain);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("uerrrr");
        }
    }

    public Story save(Story newStory) {
        List<Transaction> transactionsList = blockchain.getLatestTransactions();
        newStory.setId(transactionsList.size());
        Transaction t = new Transaction(newStory.getId(), "create", new Gson().toJson(newStory));
        blockchain.addBlock(t);

        saveBlockchain();
        return newStory;
    }

    public Story update(Story story) {
        Transaction t = new Transaction(story.getId(), "update", new Gson().toJson(story));
        blockchain.addBlock(t);

        saveBlockchain();
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
