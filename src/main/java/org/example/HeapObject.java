package org.example;

public class HeapObject {
    private String id;
    private long start;
    private long end;
    private boolean isMarked;
    private HeapObject pointsTo;

    public HeapObject(String id, long start, long end, boolean isMarked, HeapObject pointsTo) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isMarked = isMarked;
        this.pointsTo = pointsTo;
    }

    public HeapObject(String id, long start, long end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public HeapObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public HeapObject getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(HeapObject pointsTo) {
        this.pointsTo = pointsTo;
    }

    @Override
    public String toString() {
        return "" + this.id + "," +
                this.start + "," +
                this.end + "\t\t\t-->\t\t\t" +
                ((this.pointsTo == null)?"[NULL]":this.pointsTo.getId());
    }
}
