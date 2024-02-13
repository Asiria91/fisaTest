package com.prueba.fisatest.controller;

import com.prueba.fisatest.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/distance/{route}")
    public ResponseEntity<?> getDistanceOfRoute(@PathVariable List<Character> route) {
        int distance = graphService.getDistance(route);
        if (distance == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SUCH ROUTE");
        }
        return ResponseEntity.ok(distance);
    }

    @GetMapping("/trips/{start}/{end}/{maxStops}")
    public ResponseEntity<?> countTrips(@PathVariable char start, @PathVariable char end, @PathVariable int maxStops) {
        int count = graphService.countRoutes(start, end, maxStops, null, Integer.MAX_VALUE);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/shortestRoute/{start}/{end}")
    public ResponseEntity<?> shortestRoute(@PathVariable char start, @PathVariable char end) {
        int shortest = graphService.shortestRoute(start, end);
        if (shortest == Integer.MAX_VALUE) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO SUCH ROUTE");
        }
        return ResponseEntity.ok(shortest);
    }

    @GetMapping("/countRoutes/{start}/{end}/{maxStops}/{maxDistance}")
    public ResponseEntity<?> countRoutes(@PathVariable char start, @PathVariable char end,
                                         @PathVariable int maxStops, @PathVariable int maxDistance) {
        int count = graphService.countRoutes(start, end, maxStops, null, maxDistance);
        return ResponseEntity.ok(count);
    }
}
