package com.xujie.route;


public class RouteCalculatorApplication {

    public static void main(String... args) {
        long start = System.currentTimeMillis();
        RouteCalculator routeCalculator = new RouteCalculator();
        routeCalculator.findRoute("Frankfurt", "Munchen", new RouteList());
        routeCalculator.printCorrectRouteList();
        System.out.println("Cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}
