package io.github;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        Block block = new Block(0, null, "First Block");
        block.mineBlock(difficulty);
        blockchain.add(block);
        System.out.println(block.toString());

        Block block2 = new Block(1, block.getCurrHash(), "Second Block");
        block2.mineBlock(difficulty);
        blockchain.add(block2);
        System.out.println(block2.toString());
    }

    public static boolean validBlock(Block newBlock, Block oldBlock) {
        if (oldBlock == null) {
            if (newBlock.getIndex() != 0) return false;
            if (newBlock.getPrevHash() != null) return false;
            if (newBlock.getCurrHash() == null || !newBlock.calcHash().equals(newBlock.getCurrHash())) return false;
            return true;
        } else {
            if (newBlock != null) {
                if (oldBlock.getIndex() + 1 != newBlock.getIndex()) return false;
                if (newBlock.getPrevHash() == null || !newBlock.getPrevHash().equals(oldBlock.getCurrHash()))
                    return false;
                if (newBlock.getCurrHash() == null || !newBlock.calcHash().equals(newBlock.getCurrHash())) return false;
                return true;
            }
            return false;
        }
    }
}
