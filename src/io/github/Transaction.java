package io.github;

public class Transaction {
    private String sender;
    private String recipient;
    private float value;

    public Transaction(String sender, String recipient, float value) {
        this.sender = sender;
        this.recipient = recipient;
        this.value = value;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public float getValue() {
        return value;
    }
}
