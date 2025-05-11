package com.cachingproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SpringBootApplication
@EnableCaching
public class CachingProxyApplication {

	public static final int DEFAULT_PORT = 3000;
    public static final String DEFAULT_ORIGIN_URL = "http://dummyjson.com";
    public static final String ORIGIN_ARG = "origin";
    public static final String PORT_ARG = "port";
    
	public static void main(String[] args) {
		final int port = getPortFromCmdLine(args);
        final String origin = getOriginFromCmdLine(args);

        new SpringApplication(CachingProxyApplication.class)
            .run("--server.port=" + port, "--global.origin=" + origin);
	}

	private static int getPortFromCmdLine(String[] args) {
		 final var source = new SimpleCommandLinePropertySource(args);
	        return source.containsProperty(PORT_ARG) ?
	               Integer.parseInt(source.getProperty(PORT_ARG)) : DEFAULT_PORT;
	}

	private static String getOriginFromCmdLine(String[] args) {
		 final var source = new SimpleCommandLinePropertySource(args);
	        return source.containsProperty(ORIGIN_ARG) ?
	              source.getProperty(ORIGIN_ARG) : DEFAULT_ORIGIN_URL;
	}

}