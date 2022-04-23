package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class G1 {
    private final String heapPath;
    private final String pointersPath;
    private final String rootPath;
    private final String newHeapPath;
    private final Heap heap;

    public G1(String heapPath,
              String pointersPath,
              String rootPath,
              String newHeapPath,
              int heapSize,
              int regionCount) {
        this.heapPath = heapPath;
        this.pointersPath = pointersPath;
        this.rootPath = rootPath;
        this.newHeapPath = newHeapPath;
        this.heap = new Heap(heapSize, regionCount);
        start();
    }

    private void start() {
        try {
            connectRegions(fillRegions(this.heap, this.heapPath), this.pointersPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void markAndSweep() {
        try {
            sweepRegions(markRegions(this.heap, this.rootPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void de_fragmentRegions() {
        heap.de_fragment();
    }

    public void output() {
        this.outputToFile(this.newHeapPath);
    }

    private void outputToFile(String newHeapPath) {
        try {
            FileWriter fileWriter = new FileWriter(newHeapPath);
            fileWriter.write("");
            for (HeapRegion region : this.heap.getRegions()) {
                for (HeapObject object : region.getObjects()) {
                    fileWriter.append(object.toString()).append("\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Heap fillRegions(Heap heap, String heapPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(heapPath));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null) {
            String[] row = bufferLine.split(",");
            HeapObject object = new HeapObject(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]));
            this.heap.addObjectToHeap(object);
        }
        reader.close();
        return heap;
    }

    private Heap connectRegions(Heap heap, String pointersPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pointersPath));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null) {
            String[] row = bufferLine.split(",");
            heap.connect(row[0], row[1]);
        }
        reader.close();
        return heap;
    }

    private Heap markRegions(Heap heap, String rootPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rootPath));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null) {
            heap.mark(bufferLine);
        }
        reader.close();
        return heap;
    }

    private Heap sweepRegions(Heap heap) {
        heap.sweepRegion(false);
        return heap;
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
