package io.wollinger.powercompare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PowerCompare {
    public static void main(String[] args) {
        if(args.length < 2)
            System.out.println("Please provide atleast two paths!");

        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        if(!file1.exists()) {
            System.out.println("File 1 does not exist.");
            System.exit(0);
        }

        if(!file2.exists()) {
            System.out.println("File 2 does not exist.");
            System.exit(0);
        }

        new CompareItem(load(file1)).compare(new CompareItem(load(file2)));
        //new CompareItem(load(file2)).compare(new CompareItem(load(file1)));
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
