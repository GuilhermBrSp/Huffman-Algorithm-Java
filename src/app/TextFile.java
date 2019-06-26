package app;

public class TextFile {
	String content;
	int[] frequency;
	
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
