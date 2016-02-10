package api;

import com.gs.collections.api.map.MutableMap;
import com.gs.collections.impl.map.mutable.UnifiedMap;

import java.io.*;

class RegionalData {
    private final MutableMap<String, CountryRegion> countries;
    private final static int COUNTRY_INDEX = 0;
    private final static int CITY_INDEX = 1;
    private final static int STATE_PROVINCE_INDEX = 3;

    public RegionalData(String fileLocation){
        countries = new UnifiedMap<>();
        try{
            initRegionalData(fileLocation);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public MutableMap<String, CountryRegion> getCountries() {
        return countries;
    }

    private void initRegionalData(String fileLocation) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        String line;
        while((line = bufferedReader.readLine()) != null){
            String [] splitLine = line.split(",");
            processCountry(splitLine[COUNTRY_INDEX]);
            if(splitLine[COUNTRY_INDEX].equals("us")) {
                processStateProvince(splitLine[COUNTRY_INDEX], splitLine[STATE_PROVINCE_INDEX]);
                processCity(splitLine[COUNTRY_INDEX], splitLine[STATE_PROVINCE_INDEX], splitLine[CITY_INDEX]);
            }else{
                processCity(splitLine[COUNTRY_INDEX], splitLine[CITY_INDEX]);
            }
        }
    }

    private void processCountry(String country){
        if(!countries.containsKey(country)){
            countries.put(country, new CountryRegion(country));
        }
    }

    private void processStateProvince(String country, String stateProvince){
        stateProvince = stateProvince.toLowerCase();
        if(!countries.get(country).getStateProvinceMap().containsKey(stateProvince)){
            countries.get(country).getStateProvinceMap().put(stateProvince,
                    new StateProvinceRegion(stateProvince, country));
        }
    }

    private void processCity(String country, String city){
        String cityFirst = city.split(" ")[0];
        countries.get(country).getCityMap().put(cityFirst, new CityRegion(city, country, null));
    }

    private void processCity(String country, String stateProvince, String city){
        String cityFirst = city.split(" ")[0];
        stateProvince = stateProvince.toLowerCase();
        countries.get(country).getStateProvinceMap().get(stateProvince).getCityMap().put(cityFirst,
                new CityRegion(city, country, stateProvince));
    }
}

