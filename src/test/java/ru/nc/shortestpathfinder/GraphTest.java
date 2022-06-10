package ru.nc.shortestpathfinder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private final Graph graph = new Graph(new MapProperties("Human", "SSSS"));

    @Test
    void testGetVertexLinks() {
        List result = graph.getVertexLinks(0);
        List rightAnswer = new ArrayList(2);
        rightAnswer.add(new PathPrice(2,5));
        rightAnswer.add(new PathPrice(1,5));

        assertEquals(rightAnswer, result);
    }

    @Test
    void testIllegalVertexNumberInGetVertexLinks(){

        assertThrows(RuntimeException.class, ()->{
            graph.getVertexLinks(-1);
        });
    }

    @Test
    void testGetMaxVertexIndex() {
        int result = graph.getMaxVertexIndex();

        assertEquals(3, result);
    }

    @Test
    void testGetHeuristicParameter() {
        int result = graph.getHeuristicParameter(0, 3);

        assertEquals(2, result);
    }

    @Test
    void testIllegalVertexNumberInGetHeuristicParameter() {

        assertThrows(RuntimeException.class, ()->{
            graph.getHeuristicParameter(-1, 10);
        });
    }
}