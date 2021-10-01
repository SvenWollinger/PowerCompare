package io.wollinger.powercompare;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PowerCompare {
    public static void main(String[] args) {
        if(!(args.length < 2)) {
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
        } else {
            JFrame frame = new JFrame();
            frame.setTitle("PowerCompare");
            frame.setSize(512, 512);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            final File[] oldFile = new File[1];
            final File[] newFile = new File[1];

            final int[] index = {0};
            JLabel dropLabel = new JLabel("Drop file 1", JLabel.CENTER);
            dropLabel.setDropTarget(new DropTarget() {
                public synchronized void drop(DropTargetDropEvent evt) {
                    try {
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        List droppedFiles = (List) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        if(index[0] == 0) {
                            dropLabel.setText("Drop file 2");
                            oldFile[0] = (File) droppedFiles.get(0);
                            index[0]++;
                        } else {
                            dropLabel.setText("Comparing...");
                            newFile[0] = (File) droppedFiles.get(0);
                            String output = new CompareItem(load(oldFile[0])).compare(new CompareItem(load(newFile[0])));

                            String filename = "result-" + getTime() + ".txt";
                            try (PrintWriter out = new PrintWriter(filename)) {
                                out.println(output);
                            }
                            Desktop.getDesktop().edit(new File(filename));
                            index[0] = 0;
                            dropLabel.setText("Drop file 1");
                        }
                    } catch (UnsupportedFlavorException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            frame.add(dropLabel);
            frame.setVisible(true);
        }
    }

    public static String getTime() {
        return LocalDateTime.now().toString().replace(".", "_").replace(":", "_");
    }

    public static ArrayList<Data> load(File file) {
        ArrayList<Data> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.toLowerCase().startsWith("stopped") || line.toLowerCase().startsWith("running"))
                    list.add(new Data(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
