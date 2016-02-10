package api;

import com.gs.collections.api.map.MutableMap;
import com.gs.collections.api.multimap.MutableMultimap;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import com.gs.collections.impl.multimap.set.UnifiedSetMultimap;

class CountryRegion {
    private final MutableMap<String, StateProvinceRegion> stateProvinceMap;
    private final MutableMultimap<String, CityRegion> cityMap;
    private final String name;

    public CountryRegion(String name){
        stateProvinceMap = new UnifiedMap<>();
        cityMap = new UnifiedSetMultimap<>();
        this.name = name;
    }

    public MutableMap<String, StateProvinceRegion> getStateProvinceMap() {
        return stateProvinceMap;
    }

    public MutableMultimap<String, CityRegion> getCityMap() {
        return cityMap;
    }

    public String getName() {
        return name;
    }
}
