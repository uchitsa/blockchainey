package io.github;

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
        return null;
    }
}
