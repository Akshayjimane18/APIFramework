package resources;

public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	GetUpcomingMovies("/v2/movies/upcoming");
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResources() {
		return resource;
	}
}
