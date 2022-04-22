package org.example;

import java.util.LinkedList;

public class HeapObject {
    private final String id;
    private int start;
    private int end;
    private final LinkedList<HeapObject> pointsTo;
    private HeapRegion region;
    private boolean isMarked;


    public HeapObject(String id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.pointsTo = new LinkedList<>();
        this.isMarked = false;
    }

    public boolean pointsTo(HeapObject object) {
        return this.pointsTo.add(object);
    }

    public int getSize() {
        return this.end - this.start;
    }

    public String getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public HeapRegion getRegion() {
        return region;
    }

    public void setRegion(HeapRegion region) {
        this.region = region;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public LinkedList<HeapObject> getPointsToObjects() {
        return this.pointsTo;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "" + this.id + "," +
                this.start + "," +
                this.end;
    }
}
