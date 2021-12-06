# WordFisher by GSE
## Description
> Word Fisher was created as a programming project at The University of Miami. It is a Java program that has the purpose of analyzing and comparing the words from the literary texts _Moby Dick_ and _Alice in Wonderland_. 

### **WordFisher.java**
Includes the implementation of different functions to accomplish the objective of the program:

#### WordFisher(String inputTextFile, String stopwordsFile)
> The constructor. Receives an input file name and a second file name containing stopwords.

#### void getStopwords()
> Populates a stopwords list from a file containing all stopwords.

#### void buildVocabulary()
> Populates a map vocabulary from a file containing the full text in plaintext format. Characters  like “?”, “-- ”, and  “)”  are filtered out.

#### int getWordCount()
> Returns the total number of words in the text.

#### int getNumUniqueWords()
> Returns the total number of unique words in the text.

#### int getFrequency()
> Returns the word frequency for a given word. If the word does not exist in the vocabulary, -1 is returned. 

#### void pruneVocabulary()
> Removes all stopwords from vocabulary.  

#### ArrayList<String> getTopWords(int n)
> Receives an integer n and returns the top n most frequently occurring words in the text.

#### void confirmTopWords(int n)
> Helper method that takes the resulting list of _getTopWords_ method as input and prints the associated frequency. The frequencies appear in descending order. 

#### ArrayList<String> commonPopularWords(int n, WordFisher other)
> Receives an integer n and another WordFisher object (i.e. another  text) as input and returns an ArrayList of the common popular words. An empty list is returned if there are no common words. 
### **WordFisherTester.java**
> The main function of the program. It is a sample of how the program can be used.

### **Text files**
> - carroll-alice.txt: the plaintext version of _Alice in Wonderland_ by Lewis Carroll

> - moby-dick.txt: the plaintext version of _Moby Dick_ by Herman Melville

> - stopwords.txt: contains a list of stopwords (most commonly used words in the language), one word per line.

