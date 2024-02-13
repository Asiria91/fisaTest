package com.prueba.fisatest.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class GraphServiceImpl implements GraphService{
    private Map<Character, Map<Character, Integer>> graph;

    public GraphServiceImpl() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addEdge(char start, char end, int weight) {
        graph.putIfAbsent(start, new HashMap<>());
        graph.get(start).put(end, weight);
    }

    @Override
    public int getDistance(List<Character> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            char start = path.get(i);
            char end = path.get(i + 1);
            if (graph.containsKey(start) && graph.get(start).containsKey(end)) {
                distance += graph.get(start).get(end);
            } else {
                return -1;
            }
        }
        return distance;
    }

    @Override
    public int countRoutes(char start, char end, int maxStops, Integer exactStops, int maxDistance) {
        return dfsCountRoutes(start, end, maxStops, exactStops, maxDistance, 0, 0);
    }

    @Override
    public int dfsCountRoutes(char current, char end, int maxStops, Integer exactStops, int maxDistance,
                              int stops, int distance) {
        if (stops > maxStops || distance > maxDistance) {
            return 0;
        }
        if (current == end && (exactStops == null || stops == exactStops)) {
            return 1;
        }
        if (!graph.containsKey(current)) {
            return 0;
        }
        int count = 0;
        for (Map.Entry<Character, Integer> neighbor : graph.get(current).entrySet()) {
            count += dfsCountRoutes(neighbor.getKey(), end, maxStops, exactStops, maxDistance,
                    stops + 1, distance + neighbor.getValue());
        }
        return count;
    }

    @Override
    public int shortestRoute(char start, char end) {
        return dfsShortestRoute(start, end);
    }


    @Override
    public int dfsShortestRoute(char current, char end) {
        if (current == end) {
            return 0;
        }
        if (!graph.containsKey(current)) {
            return Integer.MAX_VALUE;
        }
        int shortest = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> neighbor : graph.get(current).entrySet()) {
            int dist = dfsShortestRoute(neighbor.getKey(), end);
            if (dist != Integer.MAX_VALUE) {
                shortest = Math.min(shortest, neighbor.getValue() + dist);
            }
        }
        return shortest;
    }
}

