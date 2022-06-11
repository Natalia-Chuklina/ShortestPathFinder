package ru.nc.shortestpathfinder;

import java.util.Objects;

public class PathPrice {
    private final int vertexIndex;
    private final int movePrice;

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
        return Objects.hash(this.vertexIndex, this.movePrice);
    }

    @Override
    public String toString() {
        return "PathPrice{" +
                "vertexIndex=" + this.vertexIndex +
                ", movePrice=" + this.movePrice +
                '}';
    }

    public int getVertexIndex() {
        return this.vertexIndex;
    }

    public int getMovePrice() {
        return this.movePrice;
    }
}
