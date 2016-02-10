package api;

import org.junit.Assert;
import org.junit.Test;

public class RegionalDataTest {

    @Test
    public void basicTest(){
        RegionalData regionalData = new RegionalData("src/test/resources/testdata.txt");
        Assert.assertEquals(regionalData.getCountries().size(), 2);
        Assert.assertEquals(regionalData.getCountries().get("us").getStateProvinceMap().get("ut").getCityMap().size(), 1);
        Assert.assertTrue(regionalData.getCountries().get("us").getStateProvinceMap()
                .get("ut").getCityMap().containsKey("test3"));
        Assert.assertEquals(regionalData.getCountries().get("us").getStateProvinceMap()
                .get("ut").getCityMap().get("test3").toList().get(0).getName(), "test3 date");
        Assert.assertTrue(regionalData.getCountries().get("ad").getCityMap().containsKey("test3"));
    }
}
