package com.prueba.fisatest;

import com.prueba.fisatest.service.GraphService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GraphTest {

    @Autowired
    GraphService graph;

    @Test
    public void testGetDistance() {

        graph.addEdge('A', 'B', 5);
        graph.addEdge('B', 'C', 4);
        graph.addEdge('C', 'D', 8);
        graph.addEdge('D', 'C', 8);
        graph.addEdge('D', 'E', 6);
        graph.addEdge('A', 'D', 5);
        graph.addEdge('C', 'E', 2);
        graph.addEdge('E', 'B', 3);
        graph.addEdge('A', 'E', 7);

        assertEquals(9, graph.getDistance(Arrays.asList('A', 'B', 'C')));
        assertEquals(5, graph.getDistance(Arrays.asList('A', 'D')));
        assertEquals(13, graph.getDistance(Arrays.asList('A', 'D', 'C')));
        assertEquals(22, graph.getDistance(Arrays.asList('A', 'E', 'B', 'C', 'D')));
        assertEquals(-1, graph.getDistance(Arrays.asList('A', 'E', 'D')));
    }

    @Test
    public void testCountRoutes() {
        graph.addEdge('A', 'B', 5);
        graph.addEdge('B', 'C', 4);
        graph.addEdge('C', 'D', 8);
        graph.addEdge('D', 'C', 8);
        graph.addEdge('D', 'E', 6);
        graph.addEdge('A', 'D', 5);
        graph.addEdge('C', 'E', 2);
        graph.addEdge('E', 'B', 3);
        graph.addEdge('A', 'E', 7);

        assertEquals(2, graph.countRoutes('C', 'C', 3, null, Integer.MAX_VALUE));
        assertEquals(3, graph.countRoutes('A', 'C', 4, 4, Integer.MAX_VALUE));
    }

    @Test
    public void testShortestRoute() {
        graph.addEdge('A', 'B', 5);
        graph.addEdge('B', 'C', 4);
        graph.addEdge('C', 'D', 8);
        graph.addEdge('D', 'C', 8);
        graph.addEdge('D', 'E', 6);
        graph.addEdge('A', 'D', 5);
        graph.addEdge('C', 'E', 2);
        graph.addEdge('E', 'B', 3);
        graph.addEdge('A', 'E', 7);

        assertEquals(9, graph.shortestRoute('A','C'));
        assertEquals(0, graph.shortestRoute('B','B'));
    }
}