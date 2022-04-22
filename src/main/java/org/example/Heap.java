package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Heap {
    private final ArrayList<HeapRegion> regions;
    private final HashMap<String, HeapObject> heapObjects;
    private final int regionCount;
    private final int regionSize;

    private final int heapSize;

    public Heap(int heapSize, int regionCount) {
        this.regionSize = heapSize / regionCount;
        this.regions = new ArrayList<>(this.regionSize);
        for (int i = 0; i < regionCount; i++) {
            this.regions.add(new HeapRegion(heapSize, regionCount, i));
        }
        this.heapObjects = new HashMap<>();
        this.regionCount = regionCount;
        this.heapSize = heapSize;
    }

    public boolean addObjectToHeap(HeapObject object) {
        if (object == null) {
            return false;
        }
        boolean isAdded = false;
        int i = (object.getStart() - object.getSize()) / this.regionSize;
        while (i < this.regionCount) {
            if (this.regions.get(i).addHeapObject(object)) {
                isAdded = true;
                break;
            }
            i++;
        }
        if (isAdded) {
            this.heapObjects.put(object.getId(), object);
        }
        return isAdded;
    }


    public boolean connect(String fromId, String toId) {
//        return this.heapObject.get(fromId).getRegion().pointsTo(this.heapObject.get(toId).getRegion()) &&
        return this.heapObjects.get(fromId).pointsTo(this.heapObjects.get(toId));
    }

    public void mark(String id) {
        mark(this.heapObjects.get(id));
    }

    private void mark(HeapObject object) {
        if (object == null) {
            return;
        }
        object.setMarked(true);
        object.getRegion().setMarked(true);
        for (HeapObject obj : object.getPointsToObjects()) {
            mark(obj);
        }
    }

    public void sweepRegion(boolean unmarked){
        for (int i = 0; i < this.regionCount; i++){
            if (!this.regions.get(i).isMarked()){
                for (HeapObject object: this.regions.get(i).getObjects()){
                    this.heapObjects.remove(object.getId());
                }
                this.regions.set(i, new HeapRegion(heapSize,regionCount,this.getRegions().get(i).getRegionId()));
            } else {
                if (unmarked) this.regions.get(i).setMarked(false);
            }
        }
    }

    public void de_fragment(){
        removeAndAdd();
        sweepMarkedRegion();
        reposition();
    }

    private void removeAndAdd(){
        for (int i = 0 ; i < this.regionCount; i++){
            HeapRegion region = this.regions.get(i);
            while (region.isMarked() && !region.getObjects().isEmpty()){
                HeapObject tempObject = region.getObjects().getFirst();
                if (tempObject.isMarked()){
                    addInOrder(tempObject);
                } else {
                    this.heapObjects.remove(tempObject.getId());
                }
                region.getObjects().remove(tempObject);
            }
        }
    }

    private boolean addInOrder(HeapObject object){
        if (object == null) {
            return false;
        }
        boolean isAdded = false;
        for (int i = 0 ; (i < this.regionCount); i++){
            if ((!this.regions.get(i).isMarked()) && this.regions.get(i).addHeapObject(object)){
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }


    private void sweepMarkedRegion(){
        for (int i = 0 ; i < this.regionCount; i++){
            if (this.regions.get(i).isMarked()){
                this.regions.set(i,new HeapRegion(this.heapSize,this.regionCount,this.regions.get(i).getRegionId()));
            }
        }
    }

    private void reposition(){
        for (HeapRegion region: this.regions){
            region.reposition();
        }
    }
    public ArrayList<HeapRegion> getRegions() {
        return regions;
    }

    public HashMap<String, HeapObject> getHeapObjects() {
        return heapObjects;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (HeapRegion region : this.regions) {
            stringBuilder.append(region.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
