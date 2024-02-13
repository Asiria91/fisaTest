package com.prueba.fisatest.service;

import java.util.List;

public interface GraphService{
    void addEdge(char start, char end, int weight);

    int getDistance(List<Character> path);

    int countRoutes(char start, char end, int maxStops, Integer exactStops, int maxDistance);

    int dfsCountRoutes(char current, char end, int maxStops, Integer exactStops, int maxDistance,
                       int stops, int distance);

    int shortestRoute(char start, char end);

    int dfsShortestRoute(char current, char end);
}
