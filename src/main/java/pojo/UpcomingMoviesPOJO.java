package pojo;

import java.util.List;

public class UpcomingMoviesPOJO {
    private List<MovieContent> upcomingMovieData;
    private List<String> keys;
    private List<String> languageScored;

    public List<MovieContent> getUpcomingMovieData() {
        return upcomingMovieData;
    }

    public void setUpcomingMovieData(List<MovieContent> upcomingMovieData) {
        this.upcomingMovieData = upcomingMovieData;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getLanguageScored() {
        return languageScored;
    }

    public void setLanguageScored(List<String> languageScored) {
        this.languageScored = languageScored;
    }
}
