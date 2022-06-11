package ru.nc.shortestpathfinder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс для чтения и генерации данных из .properties файла с конфигурацией карты.
 */
public class PropertiesReader {
    private static final String FILE_PATH = "world.properties";
    private static final String TILES_PROPERTY_KEY = "tiles";
    private final Map<String, Integer> raceTiles = new HashMap<>();

    public PropertiesReader(String race, String mapCells) {

        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            property.load(fis);


            if (race == null || race.isBlank()) {
                throw new IOException("Entity race not specified!");
            }

            String availableTiles = property.getProperty(TILES_PROPERTY_KEY);
            String availableRaceTilesCosts = property.getProperty(race);

            if (availableTiles == null) {
                throw new IOException("Tiles not found!");
            }

            if (availableRaceTilesCosts == null) {
                throw new IOException("Race not found!");
            }

            String[] tilesSplit = availableTiles.split(",");
            String[] raceTilesCostsSplit = availableRaceTilesCosts.split(",");


            if (mapCells == null || !mapCells.replaceAll("[" + availableTiles + "]", "").isBlank()
                    || !this.isPerfectSquare(mapCells.length()) || mapCells.isBlank()) {
                throw new IOException("Incorrect input game field map cells string!");
            }

            if (raceTilesCostsSplit.length == tilesSplit.length) {
                for (int i = 0; i < tilesSplit.length; i++) {
                    this.raceTiles.put(tilesSplit[i], Integer.valueOf(raceTilesCostsSplit[i]));
                }
            } else {
                throw new IOException("The number of tiles mismatch the number of race's movement costs!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPerfectSquare(int mapCellLength) {
        double sqrt = Math.sqrt(mapCellLength);
        return (sqrt - Math.floor(sqrt)) == 0;
    }

    public Map<String, Integer> getRaceTiles() {
        return this.raceTiles;
    }
}
