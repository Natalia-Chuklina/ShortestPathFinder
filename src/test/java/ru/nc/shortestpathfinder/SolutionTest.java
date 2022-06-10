package ru.nc.shortestpathfinder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    public final String DEFAULT_MAP =
                    "SSSS" +
                    "SSSS" +
                    "SSSS" +
                    "SSSS";

    public final String MAP_WITH_INDIRECT_WAY_FOR_HUMAN =
                            "SPPPS" +
                            "SSSPS" +
                            "SSPPS" +
                            "SSPSS" +
                            "SSPPP";

    @Test
    void testDefaultInputResultForHuman() {
        int result = Solution.getResult(DEFAULT_MAP, "Human");

        assertEquals(30, result);
    }

    @Test
    void testDefaultInputResultForSwamper() {
        int result = Solution.getResult(DEFAULT_MAP, "Swamper");

        assertEquals(12, result);
    }

    @Test
    void testDefaultInputResultForWoodman() {
        int result = Solution.getResult(DEFAULT_MAP, "Woodman");

        assertEquals(18, result);
    }

    @Test
    void testSpecificInputResultForHuman() {
        int result = Solution.getResult(MAP_WITH_INDIRECT_WAY_FOR_HUMAN, "Human");

        assertEquals(10, result);
    }

    @Test
    void testNullRaceArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(DEFAULT_MAP,null);
        });
    }

    @Test
    void testWrongRaceArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(DEFAULT_MAP,"Halfling");
        });
    }

    @Test
    void testBlankRaceArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(DEFAULT_MAP,"");
        });
    }

    @Test
    void testNullMapCellArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult(null,"Human");
        });
    }

    @Test
    void testBlankMapCellArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult("","Human");
        });
    }

    @Test
    void testWrongLengthMapCellArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult("SSSSS","Human");
        });
    }

    @Test
    void testWrongTileMapCellArgument(){

        assertThrows(RuntimeException.class, ()->{
            Solution.getResult("SQSS","Human");
        });
    }


}