package api;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.multimap.MutableMultimap;
import com.gs.collections.impl.list.mutable.FastList;

import java.util.Arrays;

public class RegionIdentifier {

    private final RegionalData regionalData;

    public RegionIdentifier(){
        regionalData = new RegionalData("src/main/resources/worldcitiespop.txt");
    }

    public String getCityRegionNonUS(String entityName, String country){
        MutableList<String> entityNameList = generateEntityNameList(entityName);
        try {
            CityRegion cityRegion = getCityInName(entityNameList, regionalData.getCountries().get(country).getCityMap());
            if (cityRegion == null) {
                return null;
            }
            return cityRegion.getName();
        }catch(NullPointerException e){
            return null;
        }
    }

    public String getCityRegionUS(String entityName, String stateProvince, String country){
        MutableList<String> entityNameList = generateEntityNameList(entityName);
        try{
            CityRegion cityRegion = getCityInName(entityNameList, regionalData.getCountries().get(country).getStateProvinceMap().get(stateProvince).getCityMap());
            if(cityRegion == null){
                return null;
            }
            return cityRegion.getName();
        }catch(NullPointerException e){
            return null;
        }
    }

    private CityRegion getCityInName(MutableList<String> entityNameList, MutableMultimap<String, CityRegion> cityMap){
        for(String token : entityNameList){
            if(cityMap.containsKey(token)){
                MutableList<String> entityNameSubList = entityNameList.subList(entityNameList.indexOf(token), entityNameList.size());
                MutableList<CityRegion> possibleMatches = cityMap.get(token).toList();
                CityRegion bestMatch = null;
                int missingCount = Integer.MAX_VALUE;
                for(CityRegion cityRegion : possibleMatches){
                    String [] splitName = cityRegion.getName().split(" ");
                    if(entityNameSubList.size() >= splitName.length){
                        MutableList<String> entityNameSubSubList = entityNameSubList.subList(0, splitName.length);
                        if(entityNameSubSubList.containsAll(FastList.newListWith(splitName))){
                            if (entityNameSubList.size() - splitName.length < missingCount) {
                                missingCount = entityNameSubList.size() - splitName.length;
                                bestMatch = cityRegion;
                            }
                        }
                    }
                }
                return bestMatch;
            }
        }
        return null;
    }

    private MutableList<String> generateEntityNameList(String entityName){
        return new FastList<>(Arrays.asList(basicNormalization(entityName).split(" ")));
    }

    private String basicNormalization(String entityName){
        return entityName.replaceAll("[^A-Za-z0-9\\- ]", "").replaceAll(" +", " ").trim().toLowerCase();
    }

}
