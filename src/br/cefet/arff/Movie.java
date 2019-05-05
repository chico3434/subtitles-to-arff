package br.cefet.arff;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Movie {
    private String title;
    private String rating;
    private File file;

    private Map<String, Integer> words;

    public Movie(String title, String rating) {
        this.title = title.toLowerCase();
        this.rating = rating;
        words = new HashMap<>();
    }

    public Movie(String title, String rating, File file) {
        this.title = title.toLowerCase();
        this.rating = rating;
        this.file = file;
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

    public File getFile() {
        return file;
    }
}
