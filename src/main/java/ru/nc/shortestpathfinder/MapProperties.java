package ru.nc.shortestpathfinder;

import java.util.Map;

public class MapProperties {

    private final Map<String, Integer> raceTiles;
    private final String mapCells;

    private final int fieldDimension;

    public MapProperties(String race, String mapCells) throws RuntimeException {
        PropertiesReader reader = new PropertiesReader(race, mapCells);
        this.raceTiles = reader.getRaceTiles();

        this.mapCells = mapCells;

        this.fieldDimension = (int) Math.sqrt(this.mapCells.length());
    }

    private String getTileByIndex(int index) {
        return this.mapCells.substring(index, index + 1);
    }

    public int getWeightByIndex(int index) {
        return this.raceTiles.get(this.getTileByIndex(index));
    }

    public int getFieldDimension() {
        return this.fieldDimension;
    }
}
