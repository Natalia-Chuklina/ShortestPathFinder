package ru.nc.shortestpathfinder;

public class VertexWithPriority implements Comparable<VertexWithPriority> {

    private final int index;
    private final int priority;

    public VertexWithPriority(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

    public int getIndex() {
        return this.index;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(VertexWithPriority o) {
        return Integer.compare(this.priority, o.priority);
    }
}
