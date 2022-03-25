package com.anjana.assignment2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Anjana (we can use lombok to avoid some boilerplate code)
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 * <p>
 * Data class which stores details of ip call. Since this class is cached we have to extend
 * JdkSerializationRedisSerializer and implement Serializable
 */
@Entity
public class IpData {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "query")
    private String query;
    @Column(name = "status")
    private String status;
    @Column(name = "country")
    private String country;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "region")
    private String region;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "city")
    private String city;
    @Column(name = "zip")
    private String zip;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lon")
    private String lon;
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "isp")
    private String isp;
    @Column(name = "org")
    private String org;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
}
