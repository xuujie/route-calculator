package com.xujie.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteCalculator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<Route> deadRoutes = new ArrayList<>();

    private List<RouteList> correctRouteList = new ArrayList<>();

    public void findRoute(String from, String to, RouteList routeList) {
        List<Route> routes = getRoutes(from);
        boolean singleRoute = routes.size() == 1;

        //all reversed routes are dead routes
        routes.forEach(route -> deadRoutes.add(getRoute(route.getTo(), route.getFrom())));

        for (Route route : routes) {
            if (route.getTo().equals(to)) {
                routeList.addRoute(route);
                logger.debug("Correct route: " + routeList);
                correctRouteList.add(routeList.copy());
                routeList.clear();
            } else if (deadRoutes.contains(route)) {
                if (singleRoute) {
                    Route reversedRoute = getReversedRoute(route);
                    routeList.removeRoute(reversedRoute);
                    deadRoutes.add(reversedRoute);
                    logger.debug("Dead route: " + route + " and its reversed route " + reversedRoute);
                }
            } else {
                routeList.addRoute(route);
                RouteList routeListCopy = routeList.copy();
                findRoute(route.getTo(), to, routeList);
                if (routeList.equals(routeListCopy)) { //no change, means no route found
                    routeList.removeLastRoute(); //trace back
                }
            }
        }
    }

    //solution 2, multiple threading

    public void printCorrectRouteList() {
        correctRouteList.stream().sorted().map(routeList -> "Route: " + routeList + ", Total Distance: " + routeList.distance()).forEach(System.out::println);
    }

    public List<Route> getRoutes(String from) {
        return RouteData.allRoutes.stream().filter(route -> route.getFrom().equals(from)).collect(Collectors.toList());
    }

    public Route getReversedRoute(Route route) {
        return RouteData.allRoutes.stream().filter(reverseRoute -> reverseRoute.getFrom().equals(route.getTo()) && reverseRoute.getTo().equals(route.getFrom())).findFirst().orElse(null);
    }

    public Route getRoute(String from, String to) {
        return RouteData.allRoutes.stream().filter(route -> route.getFrom().equals(from) && route.getTo().equals(to)).findFirst().orElse(null);
    }
}
