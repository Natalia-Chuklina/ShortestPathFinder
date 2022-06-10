package ru.nc.shortestpathfinder;

import java.util.Objects;

public class PathPrice {
    private int vertexIndex;
    private int movePrice;

    public PathPrice(int vertexIndex, int movePrice) {
        this.vertexIndex = vertexIndex;
        this.movePrice = movePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        PathPrice pathPrice = (PathPrice) o;
        return this.vertexIndex == pathPrice.vertexIndex && this.movePrice == pathPrice.movePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexIndex, movePrice);
    }

    @Override
    public String toString() {
        return "PathPrice{" +
                "vertexIndex=" + vertexIndex +
                ", movePrice=" + movePrice +
                '}';
    }

    public int getVertexIndex() {
        return vertexIndex;
    }

    public int getMovePrice() {
        return movePrice;
    }
}
