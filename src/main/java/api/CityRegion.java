package api;

class CityRegion {
    private final String name;
    private final String country;
    private final String stateProvince;

    public CityRegion(String name, String country, String stateProvince){
        this.name = name;
        this.country = country;
        this.stateProvince = stateProvince;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getStateProvince() {
        return stateProvince;
    }

}
