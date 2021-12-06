import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordFisher {
	public HashMap<String,Integer> vocabulary;
	private List<String> stopwords;
	private String inputTextFile;
	private String stopwordsFile;
	
	
	private static class WordNode {
		private String word;
		private Integer frequency;
		
		public WordNode(String word, Integer frequency) {
			this.word = word;
			this.frequency = frequency;
		}
	}
	
	private class FrequencyComparator implements Comparator<WordNode> {

		@Override
		public int compare(WordNode word1, WordNode word2) {
			
			if (word1.frequency < word2.frequency) {
				return 1;
			}
			
			else if (word1.frequency == word2.frequency) {
				return 0;
			}
			
			return -1;
		}
		
	}
		
	public WordFisher(String inputTextFile, String stopwordsFile) {
		
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;
		
		getStopwords();
		buildVocabulary();
	
	}
	
	private void getStopwords() {
		try {
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
		
			String line;
			List<String> list = new ArrayList<String>();
			
			try {
				while((line = input.readLine()) != null) {
					list.add(line);
				}
				
				input.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			this.stopwords = list;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void buildVocabulary() {
		
		try {
			
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));
			reader = reader.toLowerCase();
			reader = reader.replaceAll("[^a-zA-Z0-9 ]", "");
			
			String[] allWords = reader.split("\\s+");
			
			vocabulary = new HashMap<String, Integer>();
			
			for (int i = 0; i < allWords.length; i++ ) {
				int frequency = 0;
				if (vocabulary.containsKey(allWords[i])){
					frequency = vocabulary.get(allWords[i]);
					
				}
				
				vocabulary.put(allWords[i], frequency + 1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	public int getWordCount() {
		int wordCount = 0;
		
		for(Integer value: vocabulary.values()) {
			wordCount += value;
		}
		
		return wordCount;
	}
	
	public int getNumUniqueWords() {		
		return vocabulary.size();
	}
	
	public int getFrequency(String word) {
		
		if (vocabulary.containsKey(word)) {
			return vocabulary.get(word);
		}
		
		return -1;
	}
	
	public void pruneVocabulary() {

		for (int i = 0; i < stopwords.size(); i++ ) {
			if (vocabulary.containsKey(stopwords.get(i))) {
				vocabulary.remove(stopwords.get(i));
			}
		}
	}
	
	public ArrayList<String> getTopWords(int n) {

		FrequencyComparator comparator = new FrequencyComparator();
		PriorityQueue<WordNode> frequencyPQ = new PriorityQueue<WordNode>(comparator);
		
		for (Entry<String, Integer> entry : vocabulary.entrySet()) {
			  String key = entry.getKey();
			  Integer value = entry.getValue();
			  WordNode x = new WordNode(key,value);
			  frequencyPQ.offer(x);
			}
		
		ArrayList<String> topWords = new ArrayList<String>(n) ;
		
		for (int i = 0; i < n; i++) {
			topWords.add(frequencyPQ.poll().word);
		} 
		
		return topWords;
		
	}
	
	public void confirmTopWords (int n) {
		ArrayList<String> list = this.getTopWords(n);
		ArrayList<Integer> frequencies = new ArrayList<Integer>(n);
		
		for (int i = 0; i < list.size(); i++) {
			String word = list.get(i);
			int freq = vocabulary.get(word);
			frequencies.add(freq);
		}
		
		System.out.println("\t\t Freq. = " + frequencies);
	}
	
	public ArrayList<String> commonPopularWords(int n, WordFisher other) {

		ArrayList<String> listOne = this.getTopWords(n);
		ArrayList<String> listTwo = other.getTopWords(n);
		
		listOne.retainAll(listTwo);
		
		return listOne;
	}

}