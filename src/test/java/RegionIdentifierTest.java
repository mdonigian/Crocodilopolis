import api.RegionIdentifier;
import org.junit.Assert;
import org.junit.Test;

public class RegionIdentifierTest {

    @Test
    public void basicTest(){
        RegionIdentifier regionIdentifier = new RegionIdentifier();
        Assert.assertEquals("new york", regionIdentifier.getCityRegionUS("CatPlusCo New York City", "ny", "us"));
        Assert.assertEquals("paris", regionIdentifier.getCityRegionNonUS("CatPlusCo Paris Google", "fr"));
        Assert.assertNull(regionIdentifier.getCityRegionNonUS("CatPlusCo", "fr"));
        Assert.assertNull(regionIdentifier.getCityRegionNonUS("CatPlusCo", "break"));
        Assert.assertNull(regionIdentifier.getCityRegionUS("CatPlusCo", "break", "break"));
        Assert.assertEquals("new york", regionIdentifier.getCityRegionUS("CatPlusCo NEw.+  YoR.k Ci:/ty", "ny", "us"));
    }

}
