package com.xujie.route;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouteCalculatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RouteCalculatorApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        RouteCalculator routeCalculator = new RouteCalculator();
        routeCalculator.findRoute("Frankfurt", "Munchen", new RouteList());
        routeCalculator.printCorrectRouteList();
        System.out.println("Cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}
