package api;

import com.gs.collections.api.multimap.MutableMultimap;
import com.gs.collections.impl.multimap.set.UnifiedSetMultimap;

class StateProvinceRegion {

    private final MutableMultimap<String, CityRegion> cityMap;
    private final String name;
    private final String countryName;

    public StateProvinceRegion(String name, String countryName){
        cityMap = new UnifiedSetMultimap<>();
        this.countryName = countryName;
        this.name = name;
    }

    public MutableMultimap<String, CityRegion> getCityMap() {
        return cityMap;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }

}
