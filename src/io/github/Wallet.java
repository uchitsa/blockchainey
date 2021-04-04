package io.github;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Wallet {
    public String privateKey;
    public String publicKey;
    private float balance = 100.0f;
    private ArrayList<Block> blockchain = new ArrayList<>();

    public Wallet(ArrayList<Block> blockchain) {
        generateKeyPair();
        this.blockchain = blockchain;
    }

    private void generateKeyPair() {
        try {
            String algorithm = "RSA";
            KeyPair keyPair = KeyPairGenerator.getInstance(algorithm).generateKeyPair();
            privateKey = keyPair.getPrivate().toString();
            publicKey = keyPair.getPublic().toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public float getBalance() {
        float total = balance;
        for (Block block : blockchain) {
            for (int j = 0; j < block.getTransactions().size(); j++) {
                Transaction tx = block.getTransactions().get(j);
                if (tx.getRecipient().equals(publicKey)) total += tx.getValue();
                if (tx.getSender().equals(publicKey)) total -= tx.getValue();
            }
        }
        return total;
    }

    public Transaction send(String recipient, float value) {
        if (getBalance() < value) {
            System.out.println("Not Enough Money! Tansaction discarded");
            return null;
        }

        return new Transaction(publicKey, recipient, value);
    }
}
