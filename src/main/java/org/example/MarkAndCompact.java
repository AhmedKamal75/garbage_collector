package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkAndCompact {
    private String heapPath ;
    private String pointerPath ;
    private String rootPath ;
    private String OutPath ;

    private HashMap<String, HeapObject> heapObjects;
    Utilities utilities=new Utilities();
    ArrayList<String> roots=new ArrayList<>();

    public MarkAndCompact(String heapPath, String pointerPath, String rootPath,String OutPath) throws IOException {
        this.heapPath = heapPath;
        this.pointerPath = pointerPath;
        this.rootPath = rootPath;
        this.OutPath=OutPath;
        this.heapObjects =utilities.connectFromFile(utilities.fillFromFile(heapPath),pointerPath);
        this.roots=utilities.fillFromRoot(rootPath);
        MarkAndCompact();

    }

    public void MarkAndCompact() throws IOException {

       mark();
       compact();
    }

    public void mark(String id) {
        mark(this.heapObjects.get(id));
    }

    private void mark(HeapObject object) {
        if (object == null) {
            return;
        }
        object.setMarked(true);
        for (HeapObject obj : object.getPointsToObjects()) {
            mark(obj);
        }
    }
    private void mark(){
        for (String object:roots) {
            mark(heapObjects.get(object));
        }

    }
    private void compact(){
         int newStart=0;
        try {
            FileWriter fileWriter = new FileWriter(OutPath);
            fileWriter.write("");
            for (HeapObject heapObject:heapObjects.values()) {
                if (heapObject.isMarked())
                {
                    fileWriter.append(  heapObject.getId()+","+String.valueOf(newStart)+","+String.valueOf(newStart+(heapObject.getEnd()-heapObject.getStart()))+"\n");
                     newStart+=heapObject.getEnd()-heapObject.getStart()+1;
                }

            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
