package com.uea.team.StoryTelling.database;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.uea.team.StoryTelling.Blockchain.Blockchain;
import com.uea.team.StoryTelling.Blockchain.BlockchainNotValidException;
import com.uea.team.StoryTelling.Blockchain.Transaction;
import com.uea.team.StoryTelling.models.Story;

public class StoryRepository {

    private Blockchain blockchain;
    private Database database;

    public StoryRepository() {
        this.database = Database.INSTANCE;
        this.blockchain = database.blockchain;
    }

    public Story save(Story newStory) throws BlockchainNotValidException {
        List<Transaction> transactionsList = blockchain.getLatestTransactions();
        newStory.setId(transactionsList.size());
        Transaction t = new Transaction(newStory.getId(), "create", new Gson().toJson(newStory));
        blockchain.addBlock(t);

        database.saveBlockchain();
        return newStory;
    }

    public Story update(Story story) throws BlockchainNotValidException {
        Transaction t = new Transaction(story.getId(), "update", new Gson().toJson(story));
        blockchain.addBlock(t);

        database.saveBlockchain();
        return story;
    }

    public ArrayList<Story> getAllStories() throws BlockchainNotValidException {
        if (!blockchain.isBlockChainValid()) {
            throw new BlockchainNotValidException("blockchain is not valid");
        }

        ArrayList<Story> stories = new ArrayList<>();
        List<Transaction> transactionsList = blockchain.getLatestTransactions();

        for (Transaction t : transactionsList) {
            if (!t.getAction().equals("delete")) {
                stories.add(new Gson().fromJson(t.getData(), Story.class));
            }
        }

        return stories;
    }

    public Story geStoryById(int id) throws BlockchainNotValidException {

        if (!blockchain.isBlockChainValid()) {
            throw new BlockchainNotValidException("blockchain is not valid");
        }
        ArrayList<Story> stories = getAllStories();

        for (Story s : stories) {
            if (s.getId() == id && !s.isDeleted()) {
                return s;
            }
        }
        throw new IndexOutOfBoundsException("Id " + id + "not found on stories");
    }

    public boolean delete(Story story) throws BlockchainNotValidException {
        if (!blockchain.isBlockChainValid()) {
            throw new BlockchainNotValidException("blockchain is not valid");
        }
        story.setDeleted(true);
        Transaction t = new Transaction(story.getId(), "delete", new Gson().toJson(story));
        blockchain.addBlock(t);

        database.saveBlockchain();
        return true;
    }
}
