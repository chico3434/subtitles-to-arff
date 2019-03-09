package br.cefet.arff;

import java.util.HashMap;
import java.util.Map;

public class Movie {
    private String title;
    private String rating;

    private Map<String, Integer> words;

    public Movie(String title, String rating) {
        this.title = title;
        this.rating = rating;
        words = new HashMap<>();
    }

    public void addWord(String word) {
        if (words.containsKey(word)) {
            int num = words.get(word);
            words.remove(word);
            words.put(word, num+1);
        } else {
            words.put(word, 1);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public Map<String, Integer> getWords() {
        return words;
    }
}
