package io.wollinger.powercompare;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class PowerCompare {
    public static void main(String[] args) {
        if(args.length < 2)
            System.out.println("Please provide atleast two paths!");

        File oldFile = new File(args[0]);
        File newFile = new File(args[1]);
        if(!oldFile.exists()) {
            System.out.println("File 1 does not exist.");
            System.exit(0);
        }

        if(!newFile.exists()) {
            System.out.println("File 2 does not exist.");
            System.exit(0);
        }

        new CompareItem(load(oldFile)).compare(new CompareItem(load(newFile)));
    }

    public static ArrayList<Data> load(File file) {
        ArrayList<Data> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.toLowerCase().startsWith("stopped") || line.toLowerCase().startsWith("started"))
                    list.add(new Data(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
