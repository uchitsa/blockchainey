package io.github;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Block {
    private int index;
    private long timestamp;
    private String currHash;
    private String prevHash;
    private String data;
    private int nonce;

    public Block(int index, String prevHash, String data) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.currHash = calcHash();
        this.prevHash = prevHash;
        this.data = data;
        this.nonce = 0;
    }

    private String calcHash() {
        try {
            String input = index + timestamp + prevHash + data + nonce;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
