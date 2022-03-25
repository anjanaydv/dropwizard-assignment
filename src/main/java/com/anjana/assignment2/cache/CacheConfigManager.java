package com.anjana.assignment2.cache;

import com.anjana.assignment2.model.IpData;
import com.anjana.assignment2.service.IPService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright Any portion of this assignment's code are not allowed to use in business or production.
 *
 * Caches the ip address results for 1 min.
 *
 * @author Anjana Yadav
 */
public class CacheConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfigManager.class);

    private static CacheConfigManager cacheConfigManager = new CacheConfigManager();

    public static CacheConfigManager getInstance() {
        return cacheConfigManager;
    }

    private static LoadingCache<String, IpData> ipDataCache;

    public void initIpDataCache(IPService ipService) {
        if (ipDataCache == null) {
            ipDataCache =
                    CacheBuilder.newBuilder()
                            .concurrencyLevel(10)
                            .maximumSize(200)
                            .expireAfterAccess(1, TimeUnit.MINUTES)
                            .recordStats()
                            .build(new CacheLoader<>() {

                                @Override
                                public IpData load(String ip) {
                                    logger.info("Fetching Ip Data from DB/ Cache Miss");
                                    return ipService.getIpData(ip);
                                }
                            });
        }
    }

    public IpData getIpDataFromCache(String key) {
        try {
            CacheStats cacheStats = ipDataCache.stats();
            logger.info("CacheStats = {} ", cacheStats);
            return ipDataCache.get(key);
        } catch (ExecutionException e) {
            logger.error("Error Retrieving Elements from the Ip Data Cache"
                    + e.getMessage());
        }
        return null;
    }

}