package pojo;

import java.util.List;

public class MovieContent {
    private String provider_moviename;
    private int provider_id;
    private String moviePosterUrl;
    private String movieTitle;
    private String movie_name;
    private String language;
    private List<String> genre;
    private String releaseDate;
    private int rank;
    private String paytmMovieCode;
    private int isContentAvailable;
    private int contentId;

    public String getProvider_moviename() {
        return provider_moviename;
    }

    public void setProvider_moviename(String provider_moviename) {
        this.provider_moviename = provider_moviename;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getMoviePosterUrl() {
        return moviePosterUrl;
    }

    public void setMoviePosterUrl(String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPaytmMovieCode() {
        return paytmMovieCode;
    }

    public void setPaytmMovieCode(String paytmMovieCode) {
        this.paytmMovieCode = paytmMovieCode;
    }

    public int getIsContentAvailable() {
        return isContentAvailable;
    }

    public void setIsContentAvailable(int isContentAvailable) {
        this.isContentAvailable = isContentAvailable;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
}
