package org.example;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/heap.csv";
        String pointersPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/pointers.csv";
        try {
            HashMap<String, HeapObject> heapMap = Utilities.getHeap(heapPath);
            Utilities.connectHeap(heapMap,pointersPath);
            for (HeapObject object: heapMap.values()){
                System.out.println(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}