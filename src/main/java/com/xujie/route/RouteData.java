package com.xujie.route;

import java.util.HashSet;
import java.util.Set;

public class RouteData {
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
}
