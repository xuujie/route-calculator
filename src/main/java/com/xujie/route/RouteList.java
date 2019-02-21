package com.xujie.route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteList {

    private boolean live;

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

    public void removeLastRoute() {
        if (routes.size() >= 1) {
            this.routes.remove(routes.size() - 1);
        }
    }

    public void addRouteList(RouteList routeList) {
        this.routes.addAll(routeList.getRoutes());
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
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
        String routePrint = "";
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            if (i != 0) routePrint += " | ";
            routePrint += route.getFrom() + " -> " + route.getTo() + " (" + route.getDistance() + ")";
        }

        return routePrint;
    }

    public String print() {
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
}
