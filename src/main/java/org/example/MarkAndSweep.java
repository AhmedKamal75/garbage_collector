package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkAndSweep {
    private final HashMap<String, HeapObject> heapObjects;
    Utilities utilities = new Utilities();
    ArrayList<String> roots;
    private String OutPath;

    public MarkAndSweep(String heapPath, String pointerPath, String rootPath, String OutPath) throws IOException {
        this.OutPath = OutPath;
        this.heapObjects = utilities.connectFromFile(utilities.fillFromFile(heapPath), pointerPath);
        this.roots = utilities.fillFromRoot(rootPath);
        for (String str : roots)
            Mark(heapObjects.get(str));
        Sweep();
    }

    private void Mark(HeapObject object) {
        if (object == null)
            return;
        object.setMarked(true);
        for (HeapObject heapObject : object.getPointsToObjects()) {
            Mark(heapObject);
        }
    }

    void Sweep() {
        for (HeapObject heapObject : heapObjects.values()) {
            if (!heapObject.isMarked()) {
                heapObjects.remove(heapObject);
            }
        }
        try {
            int newStart = 0;
            FileWriter fileWriter = new FileWriter(OutPath);
            fileWriter.write("");
            for (HeapObject heapObject : heapObjects.values()) {
                fileWriter.append(heapObject.getId() + "," + newStart + "," + (newStart + (heapObject.getEnd() - heapObject.getStart())) + "\n");
                newStart += heapObject.getEnd() - heapObject.getStart() + 1;
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
