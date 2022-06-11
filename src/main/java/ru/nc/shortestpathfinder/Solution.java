package ru.nc.shortestpathfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solution {
    private final static Logger log = LoggerFactory.getLogger(Solution.class.getName());

    /**
     * Возвращает минимальную стоимость перемещения из левой верхней клетки карты в правую нижнюю клетку.
     *
     * @param mapCells   строка, описывающая клетки игрового поля размера n*n
     * @param entityRace раса существа
     * @return минимальная стоимость перемещения.
     */
    public static int getResult(String mapCells, String entityRace) {
        try {
            MapProperties mapProperties = new MapProperties(entityRace, mapCells);

            Graph graph = new Graph(mapProperties);

            PathFinder pathFinder = new PathFinder(graph);

            return pathFinder.findPathFromTopLeftCellToBottomRightCell();

        } catch (RuntimeException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
