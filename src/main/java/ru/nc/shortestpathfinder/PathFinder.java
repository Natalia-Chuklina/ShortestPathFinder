package ru.nc.shortestpathfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {
    private static final Logger log = LoggerFactory.getLogger(PathFinder.class.getName());
    private static final Integer INITIAL_VERTEX = 0;
    private final Graph graph;
    private Queue<VertexWithPriority> vertexQueue = new PriorityQueue<>();
    private Map<Integer, Integer> vertexAttendanceIndicator = new HashMap<>();
    private Map<Integer, Integer> totalWayCost = new HashMap<>();

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    /**
     * Возвращает минимальную стоимость перемещения из левой верхней клетки карты в правую нижнюю клетку, которую вычисляет,
     * используя алгоритм обхода графа A*.
     *
     * @return минимальная стоимость перемещения.
     */
    public int findPathFromTopLeftCellToBottomRightCell() {
        this.vertexQueue.add(new VertexWithPriority(INITIAL_VERTEX, 0));
        this.vertexAttendanceIndicator.put(INITIAL_VERTEX, -1);
        this.totalWayCost.put(INITIAL_VERTEX, 0);

        while (!this.vertexQueue.isEmpty()) {
            Integer current = this.vertexQueue.poll().getIndex();

            if (current == this.graph.getMaxVertexIndex()) {
                break;
            }

            for (PathPrice link : this.graph.getVertexLinks(current)) {
                Integer localWayCost = totalWayCost.get(current) + link.getMovePrice();

                if (!this.vertexAttendanceIndicator.containsKey(link.getVertexIndex()) || localWayCost < totalWayCost.get(link.getVertexIndex())) {
                    totalWayCost.put(link.getVertexIndex(), localWayCost);
                    this.vertexQueue.add(new VertexWithPriority(link.getVertexIndex(), localWayCost + graph.getHeuristicParameter(graph.getMaxVertexIndex(), link.getVertexIndex())));
                    this.vertexAttendanceIndicator.put(link.getVertexIndex(), current);
                }
            }
        }
        logPath();
        return totalWayCost.get(graph.getMaxVertexIndex());
    }

    private void logPath() {
        String local = "" + this.graph.getMaxVertexIndex();
        int vertex = this.graph.getMaxVertexIndex();
        while (vertex != 0) {
            local = local + "<-" + this.getPreviousVertex(vertex);
            vertex = this.getPreviousVertex(vertex);
        }
        log.info("{}", local);
    }

    private Integer getPreviousVertex(Integer vertex) {
        return this.vertexAttendanceIndicator.get(vertex);
    }
}
