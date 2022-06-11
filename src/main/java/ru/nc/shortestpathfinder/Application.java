package ru.nc.shortestpathfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class.getName());
    private static final String field = "SSSS";
    private static final String race = "Human";

    public static void main(String[] args) {
        log.info("Application start.");


        log.info("The result is {}", Solution.getResult(field, race));

    }
}
