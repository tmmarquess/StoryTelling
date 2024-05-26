package com.uea.team.StoryTelling.Blockchain;

public class Transaction {
    private int entityId;
    private String action; // "create", "update", "delete"
    private String data;

    public Transaction(int entityId, String action, String data) {
        this.entityId = entityId;
        this.action = action;
        this.data = data;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Transaction [entityId=" + entityId + ", action=" + action + ", data=" + data + "]";
    }

}
