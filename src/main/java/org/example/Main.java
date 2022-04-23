package org.example;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    /*public static void main(String[] args) {
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
    }*/

    public static void main(String[] args) throws IOException {
        //String heapPath = "G:/fourth term/Programming Paradigms/labs/GarbageCollector/garbage_collector/src/main/resources/old/heap.csv";
        //String pointersPath = "G:/fourth term/Programming Paradigms/labs/GarbageCollector/garbage_collector/src/main/resources/old/pointers.txt";
        //String rootPath = "G:/fourth term/Programming Paradigms/labs/GarbageCollector/garbage_collector/src/main/resources/old/roots.txt";
        //String new_heapPath = "G:/fourth term/Programming Paradigms/labs/GarbageCollector/garbage_collector/src/main/resources/new/ MC_out.csv";
        int heapSize = 64;
        int regionCount = 16;
        //G1 g1 = new G1(heapPath,pointersPath,rootPath,new_heapPath,heapSize,regionCount);
        //System.out.println(g1);
        //g1.markAndSweep();
        //System.out.println(g1);
        //g1.de_fragmentRegions();
        //System.out.println(g1);
        //g1.output();
        //MarkAndCompact markAndCompact = new MarkAndCompact(heapPath, pointersPath, rootPath, new_heapPath);
    }
}