package com.anjana.assignment2.service;

import com.anjana.assignment2.dao.IpDAO;
import com.anjana.assignment2.model.IpData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Anjana Yadav
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 * <p>
 * This service class is used to fetch data from api if not exist in db. Cache the result.
 */

public class IPService {
    private IpDAO ipDAO;
    private HttpClient httpClient;
    private final String API_URL = "http://ip-api.com/json/";
    Logger logger = LoggerFactory.getLogger(IPService.class);

    public IPService(HttpClient httpClient, IpDAO ipDAO) {
        this.ipDAO = ipDAO;
        this.httpClient = httpClient;
    }

    public IpData getIpData(String ip) {
        IpData result = ipDAO.findIpData(ip);
        logger.info("Data found in db for ip: {} ", ip);
        if (result == null) {
            final String uriString = API_URL + ip;
            try {
                HttpGet request = new HttpGet(uriString);
                logger.debug("Retrieving data for ip: {} ", ip);
                HttpResponse response = httpClient.execute(request);
                System.out.println(response.toString());

                Header encodingHeader = response.getEntity().getContentEncoding();

//                Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8 :
//                        Charsets.toCharset(encodingHeader.getValue());

                String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                IpData ipData = mapper.readValue(json, IpData.class);
                System.out.println(ipData.toString());
                ipDAO.create(ipData);
                return ipData;
            } catch (IOException e) {
                logger.error("Failed to retrieved ip details from api error: {}", e.getMessage());
            }
        }
        if (result == null) {
            logger.error("Failed to retrieve data for ip: {}", ip);
        }
        return result;
    }
}