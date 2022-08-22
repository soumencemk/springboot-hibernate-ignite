package com.soumen.poc.ignitevalidatorpoc;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class IgniteConfig {

    @Value("${app.ignite.address}")
    private String ignite_address;
    @Value("${app.ignite.instance_name}")
    private String ignite_instance_name;

    public static CacheConfiguration createAtomicCache(String cacheName) {
        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setCacheMode(CacheMode.PARTITIONED);
        configuration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        configuration.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
        configuration.setName(cacheName);
        return configuration;
    }

    public CacheConfiguration createTransactionalCache(String cacheName) {
        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setCacheMode(CacheMode.PARTITIONED);
        configuration.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        configuration.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
        configuration.setName(cacheName);
        return configuration;
    }

    @Bean
    public IgniteConfiguration igniteClientConfiguration() {
        List<CacheConfiguration> cacheList = createCacheList();
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(true);
        igniteConfiguration.setIgniteInstanceName(ignite_instance_name);
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setCacheConfiguration(cacheList.toArray(new CacheConfiguration[cacheList.size()]));
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList(ignite_address));
        discoverySpi.setIpFinder(ipFinder);
        igniteConfiguration.setDiscoverySpi(discoverySpi);
        return igniteConfiguration;

    }

    public static IgniteConfiguration igniteServerConfiguration() {
        List<CacheConfiguration> cacheList = createCacheList();
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(false);
        igniteConfiguration.setIgniteInstanceName("test-soumen");
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setCacheConfiguration(cacheList.toArray(new CacheConfiguration[cacheList.size()]));
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList("10.95.31.66:47500"));
        discoverySpi.setIpFinder(ipFinder);
        igniteConfiguration.setDiscoverySpi(discoverySpi);
        return igniteConfiguration;

    }

    public static List<CacheConfiguration> createCacheList() {
        List<CacheConfiguration> cacheList = new ArrayList<>();
        //cacheList.add(createTransactionalCache("com.soumen.poc.ignitevalidatorpoc.Employee"));
        cacheList.add(createAtomicCache("org.hibernate.cache.spi.UpdateTimestampsCache"));
        cacheList.add(createAtomicCache("org.hibernate.cache.internal.StandardQueryCache"));
        return cacheList;
    }
}
