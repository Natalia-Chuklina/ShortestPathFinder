package ru.nc.shortestpathfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания, хранения и заполнения графа, отражающего все возможные перемещения по карте размера n*n.
 * Нумерация вершин графа начинается с 0 и до (n*n - 1), что соответствует нумерации клеток поля начиная с нулевой клетки
 * в направлении слева направо и сверху вниз.
 */
public class Graph {

    private static final Logger log = LoggerFactory.getLogger(Graph.class.getName());
    private static final int MAX_WAY_VALUE = 4;
    private List<List<PathPrice>> adjacencyList;

    final int vertexCount;
    final int fieldDimension;


    /**
     * Конструирует граф со стоимостями перемещений по клеткам, используя информацию из MapProperties.
     *
     * @param mapProperties свойства карты
     */
    public Graph(MapProperties mapProperties) {
        this.fieldDimension = mapProperties.getFieldDimension();
        this.vertexCount = fieldDimension * fieldDimension;

        this.adjacencyList = new ArrayList<>(vertexCount);

        for (int i = 0; i < vertexCount; i++) {
            adjacencyList.add(new ArrayList<>(MAX_WAY_VALUE));
        }

        for (int i = 0; i < vertexCount - 1; i++) {
            if (i + fieldDimension < vertexCount) {
                int bindingVertexIndex = i + fieldDimension;
                this.adjacencyList.get(i).add(createPathPrice(bindingVertexIndex, mapProperties));
            }

            if (i - fieldDimension >= 0) {
                int bindingVertexIndex = i - fieldDimension;
                this.adjacencyList.get(i).add(createPathPrice(bindingVertexIndex, mapProperties));
            }

            if ((i + 1) % fieldDimension != 0) {
                int bindingVertexIndex = i + 1;
                this.adjacencyList.get(i).add(createPathPrice(bindingVertexIndex, mapProperties));
            }

            if (i - 1 >= 0 && i % fieldDimension != 0) {
                int bindingVertexIndex = i - 1;
                this.adjacencyList.get(i).add(createPathPrice(bindingVertexIndex, mapProperties));
            }
        }


        for (int i = 0; i < vertexCount; i++) {
            log.info("Vertex{}: {}", i, adjacencyList.get(i));
        }
        log.info("Field dimension {}", mapProperties.getFieldDimension());
    }

    private PathPrice createPathPrice(int bindingVertexIndex, MapProperties mapProperties) {
        int weight = mapProperties.getWeightByIndex(bindingVertexIndex);
        return new PathPrice(bindingVertexIndex, weight);
    }

    public List<PathPrice> getVertexLinks(Integer vertex) {
        if (vertex < 0 || vertex > this.getMaxVertexIndex()) {
            throw new IllegalArgumentException("Illegal vertex number!");
        }
        return new ArrayList<>(this.adjacencyList.get(vertex));
    }

    public int getMaxVertexIndex() {
        return vertexCount - 1;
    }

    public int getHeuristicParameter(int vertexAIndex, int vertexBIndex) {
        if (vertexAIndex < 0 || vertexAIndex > this.getMaxVertexIndex() ||
                vertexAIndex < 0 || vertexAIndex > this.getMaxVertexIndex()) {
            throw new IllegalArgumentException("Illegal vertex number!");
        }

        int ax = vertexAIndex / fieldDimension;
        int bx = vertexBIndex / fieldDimension;

        int ay = vertexAIndex % fieldDimension;
        int by = vertexBIndex % fieldDimension;

        return Math.abs(ax - bx) + Math.abs(ay - by);
    }
}
