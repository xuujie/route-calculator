package com.xujie.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteList implements Comparable<RouteList> {

    private List<Route> routes = new ArrayList<>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void removeRoute(Route route) {
        this.routes.remove(route);
    }

    public Route getLastRoute() {
        if (routes.size() >= 1) {
            return routes.get(routes.size() - 1);
        } else {
            return null;
        }
    }

    public void removeLastRoute() {
        if (routes.size() >= 1) {
            this.routes.remove(routes.size() - 1);
        }
    }

    public void addRouteList(RouteList routeList) {
        this.routes.addAll(routeList.getRoutes());
    }

    public RouteList copy() {
        RouteList routeList = new RouteList();
        for (Route route : routes) {
            routeList.addRoute(route);
        }
        return routeList;
    }

    public void clear() {
        routes.clear();
    }

    public int distance() {
        int distance = 0;
        for (Route route : routes) {
            distance += route.getDistance();
        }
        return distance;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            if (i == 0) s += route.getFrom();
            s += " -(" + route.getDistance() + ")-> " + route.getTo();
        }
        return s;
    }

    public List<String> getFroms() {
        return this.routes.stream().map(Route::getFrom).collect(Collectors.toList());
    }

    @Override
    public int compareTo(RouteList o) {
        return this.distance() - o.distance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteList routeList = (RouteList) o;
        return routes.equals(routeList.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }
}
