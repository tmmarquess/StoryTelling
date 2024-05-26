package com.uea.team.StoryTelling.database;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.uea.team.StoryTelling.Blockchain.Blockchain;
import com.uea.team.StoryTelling.Blockchain.BlockchainNotValidException;

public class Database {
    public static Database INSTANCE = new Database();
    public Blockchain blockchain;
    private String blockchainFileName = "./database.blkc";

    public Database() {
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

    public void saveBlockchain() throws BlockchainNotValidException {
        if (!blockchain.isBlockChainValid()) {
            throw new BlockchainNotValidException("blockchain is not valid");
        }
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
}
