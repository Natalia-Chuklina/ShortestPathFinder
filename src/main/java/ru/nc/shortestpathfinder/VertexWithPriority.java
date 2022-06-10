package ru.nc.shortestpathfinder;

public class VertexWithPriority implements Comparable<VertexWithPriority> {

    private final int index;
    private final int priority;

    public VertexWithPriority(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

    public int getIndex() {
        return index;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(VertexWithPriority o) {
        return Integer.compare(this.priority, o.priority);
    }
}
