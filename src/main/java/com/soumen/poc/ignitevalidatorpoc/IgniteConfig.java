package com.soumen.poc.ignitevalidatorpoc;

import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {

    /*@Bean
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setClientMode(true);
        igniteConfiguration.setIgniteInstanceName("test-soumen");
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList("10.95.31.65:47500"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
        return igniteConfiguration;
    }

    @Bean
    IgniteSpringBean igniteSpringBean(IgniteConfiguration igniteConfiguration) {
        IgniteSpringBean igniteSpringBean = new IgniteSpringBean();
        igniteSpringBean.setConfiguration(igniteConfiguration);
        return igniteSpringBean;
    }*/

}
