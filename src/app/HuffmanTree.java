package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class HuffmanTree {
	private BinaryTree[] leafs;
	private String[] symbolsRepresentation;
	private int leafsSize;
	private BinaryTree rootNode;
	private String compressedBitString;
	private String clearContentString;
	
	public HuffmanTree() {
		leafsSize = 0;
		leafs = new BinaryTree[256];
		symbolsRepresentation = new String[256];
		for(int i = 0; i < 256; i++) {
			symbolsRepresentation[i] = "";
		}
		clearContentString = "";
	}

	public BinaryTree[] getleafs() {
		return leafs;
	}
	
	public BinaryTree getTree() {
		return rootNode;
	}
	// retorna a taxa de compressao do arquivo dada pela formula ((1 - (tH / tA)) * 100),
	// onde: 
	//      *tH: numero de bits utilizados para representar o conteudo do arquivo comprimido
	// 	    *tA: numero de bits utlizados para representar o conteudo do arquivo nao comprimido
	// lembrando que o caractere em java tem 2 bytes(16 bits)
	public double getCompressionRatio() {
		double tH = compressedBitString.length();
		double tA = clearContentString.length() * 16;
		return ((1 - (tH / tA)) * 100);
	}
	
	// insere uma folha no array de folhas mantendo a ordenacao decrescente do array
	public void insertTree(BinaryTree binaryTree) {
		int i = leafsSize;
		while(i > 0 && leafs[i-1].getFrequency() < binaryTree.getFrequency()) {
			leafs[i] = leafs[i-1];
			i--;
		}
		leafs[i] = binaryTree;
		leafsSize ++;
	}
	
	// remove a folha com a maior frequencia do array de folhas (presente na ultima posicao do array)
	public BinaryTree removeLeaf() {
		leafsSize --;
		return leafs[leafsSize];
	}
	
	public String getCompressedBitString() {
		return compressedBitString;
	}
	
	// popula o array de folhas e monta a arvore de huffman de maneira bottom-up
	private void mountTree(int[] characterFrequency) {
		this.populateLeafs(characterFrequency);
		this.populateNodes();
		rootNode = leafs[0];
	}
	
	// comprimi o conteudo do arquivo montando a arvore e gerando a string de bits simbolicos comprimidos
	public void compressFile(TextFile textFile) {
		this.mountTree(textFile.getFrequency());
		this.generateSymbolsRepresentation(this.getTree(), "");
		compressedBitString = this.mountBitString(textFile.getContent());
		clearContentString = textFile.getContent();
	}
	
	// monta e retorna uma string contendo os bits que representam o arquivo comprimido
	private String mountBitString(String content) {
		String bitString = "";
		for(int i = 0; i < content.length(); i++) {
			char character = content.charAt(i);
			int index = (int)character;
			bitString += symbolsRepresentation[index];
		}
		return bitString;
	}

	
	// popula a tabela de representacao de simbolos
	private void generateSymbolsRepresentation(BinaryTree tree, String representation) {
		if(tree.getRightTree() != null && tree.getLeftTree() != null) {
			generateSymbolsRepresentation(tree.getRightTree(), representation + "0");
			generateSymbolsRepresentation(tree.getLeftTree(), representation + "1");
		}
		else {
			symbolsRepresentation[(int)tree.getCharacter()] = representation;
		}
	}
	
	// popula as folhas do array de folhas mantendo a ordenacao descrescente
	private void populateLeafs(int[] characterFrequency) {
		for(int i = 0; i < 256; i ++) {
			if (characterFrequency[i] > 0) {
				char character = (char) i;
				BinaryTree frequencyLeaf = new BinaryTree(null, characterFrequency[i], character, null); 
				this.insertTree(frequencyLeaf);
			}
		}
	}
	
	// popula os nos da arvore de huffman atraves do array de folhas
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

