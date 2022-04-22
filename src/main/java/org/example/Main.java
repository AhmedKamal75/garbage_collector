package org.example;

public class Main {
//    public static void main(String[] args) {
//        String heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/heap.csv";
//        String pointersPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/pointers.csv";
//        try {
//            HashMap<String, HeapObject> heapMap = Utilities.getHeap(heapPath);
//            Utilities.connectHeap(heapMap,pointersPath);
//            for (HeapObject object: heapMap.values()){
//                System.out.println(object);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void main(String[] args) {
        String heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/heap.csv";
        String pointersPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/pointers.csv";
        String rootPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/old/roots.txt";
        String new_heapPath = "/home/ahmedkamal/IdeaProjects/GarbageCollectors/src/main/resources/new/new-heap.csv";
        int heapSize = 64;
        int regionCount = 16;
        G1 g1 = new G1(heapPath,pointersPath,rootPath,new_heapPath,heapSize,regionCount);
//        System.out.println(g1);
        g1.markAndSweep();
//        System.out.println(g1);
        g1.de_fragmentRegions();
//        System.out.println(g1);
        g1.output();
    }



}