package app;

// esta classe inicialmente seria usada para representar um arquivo lido pelo programa
public class TextFile {
	private String content;
	private int[] frequency;
	
	public TextFile(String fileContent) {
		content = fileContent;
		frequency = new int[256];
		for(int i = 0; i < 256; i++) {
			frequency[i] = 0;
		}
		for (int i = 0; i < content.length(); i++){
		    int c = (int)content.charAt(i);
		    frequency[c] += 1; 
		}
	}
	
	public int[] getFrequency() {
		return frequency;
	}
	
	public String getContent() {
		return content;
	}

}
