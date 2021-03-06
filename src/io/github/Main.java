package io.github;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        Block block = new Block(0, null, "First Block");
        block.mineBlock(difficulty);
        blockchain.add(block);
        System.out.println(block);

        Block block2 = new Block(1, block.getCurrHash(), "Second Block");
        block2.mineBlock(difficulty);
        blockchain.add(block2);
        System.out.println(block2);
        System.out.println("Current chain valid: " + validChain(blockchain));
    }

    public static boolean validChain(ArrayList<Block> blockchain) {
        if (notValidBlock(blockchain.get(0), null)) return false;

        for (int i = 1; i < blockchain.size(); i++) {
            Block currBlock = blockchain.get(i);
            Block prevBlock = blockchain.get(i - 1);
            if (notValidBlock(currBlock, prevBlock)) return false;
        }
        return true;
    }

    public static boolean notValidBlock(Block newBlock, Block oldBlock) {
        if (oldBlock == null) {
            if (newBlock.getIndex() != 0) return true;
            if (newBlock.getPrevHash() != null) return true;
            return newBlock.getCurrHash() == null || !newBlock.calcHash().equals(newBlock.getCurrHash());
        } else {
            if (newBlock != null) {
                if (oldBlock.getIndex() + 1 != newBlock.getIndex()) return true;
                if (newBlock.getPrevHash() == null || !newBlock.getPrevHash().equals(oldBlock.getCurrHash()))
                    return true;
                return newBlock.getCurrHash() == null || !newBlock.calcHash().equals(newBlock.getCurrHash());
            }
            return true;
        }
    }
}
