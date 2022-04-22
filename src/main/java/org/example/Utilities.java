package org.example;

import java.io.*;
import java.util.HashMap;

public class Utilities {
    public static HashMap<String, HeapObject> getHeap(String path) throws IOException {
        HashMap<String ,HeapObject> heap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null){
            String[] row = bufferLine.split(",");
            HeapObject object = new HeapObject(row[0],Long.parseLong(row[1]),Long.parseLong(row[2]));
            heap.put(object.getId(), object);
        }
        reader.close();
        return heap;
    }

    public static HashMap<String, HeapObject> connectHeap(HashMap<String, HeapObject> heap, String pointersPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pointersPath)));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null){
            String[] row = bufferLine.split(",");
            heap.get(row[0]).setPointsTo(heap.get(row[1]));
        }
        reader.close();
        return heap;
    }
}
