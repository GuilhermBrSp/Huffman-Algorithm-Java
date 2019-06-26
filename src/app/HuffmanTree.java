package app;

public class HuffmanTree {
	private BinaryTree[] leafs;
	private int leafsSize;
	private BinaryTree rootNode;
	
	public HuffmanTree() {
		leafsSize = 0;
		leafs = new BinaryTree[256];

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
			BinaryTree newNode = new BinaryTree(leftTree, newNodeFrequency, '-', rightTree);
			this.insertTree(newNode);
		}
	}
	
}

