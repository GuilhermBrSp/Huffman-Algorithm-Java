package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HuffmanTree {
	private BinaryTree[] leafs;
	private String[] symbolsRepresentation;
	private int leafsSize;
	private BinaryTree rootNode;
	private String compressedString;
	
	public HuffmanTree() {
		leafsSize = 0;
		leafs = new BinaryTree[256];
		symbolsRepresentation = new String[256];
		for(int i = 0; i < 256; i++) {
			symbolsRepresentation[i] = "";
		}

	}
	
	public void insertTree(BinaryTree binaryTree) {
		int i = leafsSize;
		while(i > 0 && leafs[i-1].frequency < binaryTree.frequency) {
			leafs[i] = leafs[i-1];
			i--;
		}
		leafs[i] = binaryTree;
		leafsSize ++;
	}
	
	public BinaryTree removeLeaf() {
		leafsSize --;
		return leafs[leafsSize];
	}
	
	public BinaryTree[] getleafs() {
		return leafs;
	}
	
	public BinaryTree getTree() {
		return rootNode;
	}
	
	public void mountTree(int[] characterFrequency) {
		this.populateLeafs(characterFrequency);
		this.populateNodes();
		rootNode = leafs[0];
	}
	
	public void compressFile(TextFile textFile) {
		this.mountTree(textFile.getFrequency());
		this.generateSymbolsRepresentation(this.getTree(), "");
		compressedString = this.mountBitString(textFile.getContent());
		
		
		
	}
	
	public String mountBitString(String content) {
		String bitString = "";
		for(int i = 0; i < content.length(); i++) {
			char character = content.charAt(i);
			int index = (int)character;
			bitString += symbolsRepresentation[index];
		}
		return bitString;
	}
	
	public String getCompressedString() {
		return compressedString;
	}
	
	
	private void generateSymbolsRepresentation(BinaryTree tree, String representation) {
		if(tree.right != null && tree.left != null) {
			generateSymbolsRepresentation(tree.right, representation + "0");
			generateSymbolsRepresentation(tree.left, representation + "1");
		}
		else {
			symbolsRepresentation[(int)tree.getCharacter()] = representation;
		}
	}
	
	private void populateLeafs(int[] characterFrequency) {
		for(int i = 0; i < 256; i ++) {
			if (characterFrequency[i] > 0) {
				//System.out.println(frequency);
				char character = (char) i;
				BinaryTree frequencyLeaf = new BinaryTree(null, characterFrequency[i], character, null); 
				this.insertTree(frequencyLeaf);
			}
		}
	}
	
	private void populateNodes() {
		while (leafsSize > 1) {
			BinaryTree rightTree = this.removeLeaf();
			BinaryTree leftTree = this.removeLeaf();
			int newNodeFrequency = leftTree.getFrequency() + rightTree.getFrequency();
			BinaryTree newNode = new BinaryTree(leftTree, newNodeFrequency, ' ', rightTree);
			this.insertTree(newNode);
		}
	}
	

	
}

