package com.xujie.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteCalculator {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public static Set<Route> allRoutes = new HashSet<>();

    static {
        allRoutes.add(new Route("Frankfurt", "Mannheim", 85));
        allRoutes.add(new Route("Frankfurt", "Wurzburg", 217));
        allRoutes.add(new Route("Frankfurt", "Kassel", 173));
        allRoutes.add(new Route("Mannheim", "Frankfurt", 85));
        allRoutes.add(new Route("Mannheim", "Karlsruhe", 80));
        allRoutes.add(new Route("Karlsruhe", "Mannheim", 80));
        allRoutes.add(new Route("Karlsruhe", "Augsburg", 250));
        allRoutes.add(new Route("Augsburg", "Karlsruhe", 250));
        allRoutes.add(new Route("Augsburg", "Munchen", 84));
        allRoutes.add(new Route("Wurzburg", "Frankfurt", 217));
        allRoutes.add(new Route("Wurzburg", "Erfurt", 186));
        allRoutes.add(new Route("Wurzburg", "Numberg", 103));
        allRoutes.add(new Route("Erfurt", "Wurzburg", 186));
        allRoutes.add(new Route("Numberg", "Wurzburg", 103));
        allRoutes.add(new Route("Numberg", "Stuttgart", 183));
        allRoutes.add(new Route("Numberg", "Munchen", 167));
        allRoutes.add(new Route("Stuttgart", "Numberg", 183));
        allRoutes.add(new Route("Munchen", "Augsburg", 84));
        allRoutes.add(new Route("Munchen", "Numberg", 167));
        allRoutes.add(new Route("Munchen", "Kassel", 502));
        allRoutes.add(new Route("Kassel", "Frankfurt", 173));
        allRoutes.add(new Route("Kassel", "Munchen", 502));
    }

    private List<Route> visitedRoutes = new ArrayList<>();

    private List<Route> reverseRoutes = new ArrayList<>();

    private List<RouteList> correctRouteList = new ArrayList<>();

    public void findRoute(String from, String to, RouteList routeList) {
        List<Route> routes = getRoutes(from);

        for (Route route : routes) {
            reverseRoutes.add(getRoute(route.getTo(), route.getFrom()));
        }
        logger.debug("Reversed Routes: " + reverseRoutes);

        for (Route route : routes) {
            if (route.getTo().equals(to)) {
                routeList.addRoute(route);
                logger.debug("Correct route: " + routeList);
                correctRouteList.add(routeList.copy());
                routeList.clear();
            } else if (reverseRoutes.contains(route)) {
                routeList.removeRoute(route);
                if (routes.size() == 1) { //todo: reverse all
                    routeList.removeRoute(getReversedRoute(route));
                }
                logger.debug("Dead route: " + route + " RouteList: " + routeList);
            } else {
                routeList.addRoute(route);
                findRoute(route.getTo(), to, routeList);
            }
        }
    }

    public void printCorrectRouteList() {
        correctRouteList.stream().map(routeList -> "Route: " + routeList.print() + ", Total Distance: " + routeList.distance()).forEach(System.out::println);
    }

    public List<Route> getRoutes(String from) {
        return allRoutes.stream().filter(route -> route.getFrom().equals(from)).collect(Collectors.toList());
    }

    public Route getReversedRoute(Route route) {
        return allRoutes.stream().filter(reverseRoute -> reverseRoute.getFrom().equals(route.getTo()) && reverseRoute.getTo().equals(route.getFrom())).findFirst().orElse(null);
    }

    public Route getRoute(String from, String to) {
        return allRoutes.stream().filter(route -> route.getFrom().equals(from) && route.getTo().equals(to)).findFirst().orElse(null);
    }
}
