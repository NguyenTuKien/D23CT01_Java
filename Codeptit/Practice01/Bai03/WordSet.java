package Practice01.Bai03;

import java.util.Set;
import java.util.TreeSet;

class WordSet {
    private Set<String> words;

    public WordSet(String s) {
        this.words = new TreeSet<>();
        String[] arr = s.toLowerCase().split("\\s+");
        for (String word : arr) {
            this.words.add(word);
        }
    }

    public WordSet union(WordSet other) {
        WordSet result = new WordSet("");
        result.words.addAll(this.words);
        result.words.addAll(other.words);
        return result;
    }

    public WordSet intersection(WordSet other) {
        WordSet result = new WordSet("");
        result.words.addAll(this.words);
        result.words.retainAll(other.words);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : this.words) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }
}