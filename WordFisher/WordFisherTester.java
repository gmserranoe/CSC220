public class WordFisherTester {

	public static void main(String[] args) {
		
		//MOBY DICK
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");
		
		moby.getWordCount();
		moby.getFrequency("whale");
		moby.getNumUniqueWords();
		

		System.out.println(" > Moby Dick");
		//System.out.println(moby.vocabulary.toString());
		
		System.out.println("\t - getWordCount: " + moby.getWordCount());
		System.out.println("\t - getNumUniqueWords: " + moby.getNumUniqueWords());
		System.out.println("\t - getFrequency: " + moby.getFrequency("whale"));
		moby.pruneVocabulary();
		System.out.println("\t - Word count after prunedVocabulary: " + moby.getWordCount());
		
		//ALICE IN WONDERLAND
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		
		alice.getWordCount();
		alice.getFrequency("whale");
		alice.getNumUniqueWords();
		
		System.out.println("\n > Alice in Wonderland");
		//System.out.println(alice.vocabulary.toString());
		
		System.out.println("\t - getWordCount: " + alice.getWordCount());
		System.out.println("\t - getNumUniqueWords: " + alice.getNumUniqueWords());
		System.out.println("\t - getFrequency: " + alice.getFrequency("whale"));
		alice.pruneVocabulary();
		System.out.println("\t - Word count after prunedVocabulary: " + alice.getWordCount());
		
		int n = 10; 
		System.out.println("\nComparisons");
		System.out.println("\t[For n = " + n + "]");
		System.out.println("\t - Moby's top words: " + moby.getTopWords(n));
		moby.confirmTopWords(n);
		System.out.println("\t - Alice's top words: " + alice.getTopWords(n));
		alice.confirmTopWords(n);
		System.out.println("\t - Common popular words: " + moby.commonPopularWords(20, alice));	
		
	}

}
