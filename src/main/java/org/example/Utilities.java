package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Utilities {
    public static HashMap<String, HeapObject> fillFromFile(String heapPath) throws IOException {
        HashMap<String, HeapObject> objects = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(heapPath));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null) {
            String[] row = bufferLine.split(",");
            HeapObject object = new HeapObject(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]));
            objects.put(object.getId(),object);
        }
        reader.close();
        return objects;
    }

    public static HashMap<String, HeapObject> connectFromFile(HashMap<String, HeapObject> objects, String pointersPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pointersPath));
        String bufferLine;
        while ((bufferLine = reader.readLine()) != null) {
            String[] row = bufferLine.split(",");
            objects.get(row[0]).getPointsToObjects().add(objects.get(row[1]));
        }
        reader.close();
        return objects;
    }

    public static void main(String[] args){
        String heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/heap.csv";
        String pointersPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/pointers.csv";
        String rootPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/roots.txt";
        String new_heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/new/new-heap.csv";
        try {
            HashMap<String, HeapObject> objects = connectFromFile(fillFromFile(heapPath),pointersPath);
            for (HeapObject object:
                 objects.values()) {
                System.out.println(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
