package com.anjana.assignment2;

import com.anjana.assignment2.apis.IpDataApi;
import com.anjana.assignment2.cache.CacheConfigManager;
import com.anjana.assignment2.config.MyAppConfiguration;
import com.anjana.assignment2.dao.IpDAO;
import com.anjana.assignment2.model.IpData;
import com.anjana.assignment2.service.IPService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;

/**
 * Dropwizard application
 */
public class MyApp extends Application<MyAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new MyApp().run(args);
    }

    private final HibernateBundle<MyAppConfiguration> hibernateBundle =
            new HibernateBundle<>(IpData.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MyAppConfiguration config) {
                    return config.getDataSourceFactory();
                }
            };

    @Override
    public void initialize(Bootstrap<MyAppConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MyAppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });


        bootstrap.addBundle(new AssetsBundle("/assets", "/"));
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(MyAppConfiguration config, Environment environment) {
        /**
         * DAO config
         */
        final IpDAO dao = new IpDAO(hibernateBundle.getSessionFactory());
        final HttpClient httpClient = new HttpClientBuilder(environment).using(config.getHttpClientConfiguration())
                .build(getName());
        final IPService ipService = new IPService(httpClient, dao);

        /**
         * Cache config
         */
        CacheConfigManager cacheConfigManager = CacheConfigManager
                .getInstance();
        cacheConfigManager.initIpDataCache(ipService);

        environment.jersey().register(ipService);
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new IpDataApi(dao, ipService));
    }
}
