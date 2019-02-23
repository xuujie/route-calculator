package com.xujie.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteCalculator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<Route> reverseRoutes = new ArrayList<>();

    private List<RouteList> correctRouteList = new ArrayList<>();

    public void findRoute(String from, String to, RouteList routeList) {
        List<Route> routes = getRoutes(from);

        if (routes.size() == 1) {
            routes.get(0).setSingle(true);
        }
        routes.forEach(route -> reverseRoutes.add(getRoute(route.getTo(), route.getFrom())));
        logger.debug("Reversed Routes: " + reverseRoutes);

        for (Route route : routes) {
            if (route.getTo().equals(to)) {
                routeList.addRoute(route);
                logger.debug("Correct route: " + routeList);
                correctRouteList.add(routeList.copy());
                routeList.clear();
            } else if (reverseRoutes.contains(route)) {
                routeList.removeRoute(route);
                if (routes.size() == 1) { //only have way back
                    routeList.removeRoute(getReversedRoute(route));
                }
                //if previous route are single route, keep going back
                while (routeList.getLastRoute() != null && routeList.getLastRoute().isSingle()) {
                    routeList.removeLastRoute();
                }
                logger.debug("Dead route: " + route + " RouteList: " + routeList);

            } else {
                routeList.addRoute(route);
                findRoute(route.getTo(), to, routeList);
            }
        }
    }

    public void printCorrectRouteList() {
        correctRouteList.stream().sorted().map(routeList -> "Route: " + routeList.print() + ", Total Distance: " + routeList.distance()).forEach(System.out::println);
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
