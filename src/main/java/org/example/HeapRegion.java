package org.example;

import java.util.HashSet;
import java.util.LinkedList;

public class HeapRegion {
    private final int regionId;
    private final LinkedList<HeapObject> objects;
    private final int regionSize;
    private int objectsInRegionSize;

    private boolean isMarked;

    public HeapRegion(int heapSize, int regionCount, int regionId) {
        this.regionSize = heapSize / regionCount;
        this.regionId = regionId;
        this.objectsInRegionSize = 0;
        this.isMarked = false;
        this.objects = new LinkedList<>();
    }

    public LinkedList<HeapObject> getObjects() {
        return objects;
    }

    public boolean addHeapObject(HeapObject object) {
        if (objectsInRegionSize + object.getSize() > this.regionSize) {
            return false;
        }
        this.objectsInRegionSize += object.getSize();
        object.setRegion(this);
        return this.objects.add(object);
    }

    public void reposition() {
        int i = 0;
        for (HeapObject object : this.objects) {
            int size = object.getSize();
            object.setStart(i + (this.regionSize) * this.regionId);
            object.setEnd(size + object.getStart());
            i += size;
        }
    }

    @Override
    public String toString() {
        HashSet<Integer> pointedToRegions = new HashSet<>();
        for (HeapObject object : this.objects) {
            for (HeapObject pointedToObject : object.getPointsToObjects()) {
                pointedToRegions.add(pointedToObject.getRegion().getRegionId());
            }
        }
        return this.regionId + " -(" + this.objects.size() + ")(" + this.isMarked + ")-> " + pointedToRegions;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public int getRegionId() {
        return this.regionId;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
