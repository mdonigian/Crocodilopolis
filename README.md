# Crocodilopolis
<h5>A library for parsing city data out of legal entity names</h5>

<p>
Legal entity names E.g: "Microsoft New York", "Goldman Sachs Los Angeles", "Bank of Switzerland Geneve", "Max's New York Towing Company",
often contain tokens that reflect the city that the legal entity operates in. Crocodilopolis (named after the longest continuously inhabited
city) allows this data to be parsed from these names. The current version (v 0.1) requires a country (in ISO_3166_A2) format to be provided, and for
entities located in the United States the state must be provided in the two letter format as well. The next version (v 0.2) will remove this restriction
and use a detection method to parse country and state data when it is available.
</p>

<h5>Example</h5>
```java
RegionIdentifier regionIdentifier = new RegionIdentifier();
String entityName = "Max's New York Towing Company";
String stateProvince = "ny";
String country = "us";
Assert.assertEquals("new york", regionIdentifier.getCityRegionUS(entityName, stateProvince, country));
```

<h5>Notes</h5>
The RegionIdentifier class should be instantiated only once and injected into classes that will utilize it. The instatiated class requires
~1.2GB of memory. This is because it loads a global list of cities into memory. The next version (v 0.2) will reduce this memory footprint.


