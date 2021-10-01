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

            new CompareItem(load(oldFile), oldFile.getAbsolutePath()).compare(new CompareItem(load(newFile), newFile.getAbsolutePath()));
        } else {
            JFrame frame = new JFrame();
            frame.setTitle("PowerCompare");
            frame.setSize(512, 512);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            final File[] oldFile = new File[1];
            final File[] newFile = new File[1];

            final int[] index = {0};
            final String DROP_0 = "Drop file 1 or drag two files in";
            final String DROP_1 = "Drop file 2";
            JLabel dropLabel = new JLabel(DROP_0, JLabel.CENTER);
            dropLabel.setDropTarget(new DropTarget() {
                public synchronized void drop(DropTargetDropEvent evt) {
                    try {
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        List droppedFiles = (List) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        if(droppedFiles.size() > 1) {
                            ArrayList<File> files = new ArrayList<>();
                            for(Object file : droppedFiles) {
                                files.add((File) file);
                            }
                            evaluateToFile(files.get(0), files.get(1));
                            dropLabel.setText(DROP_0);
                        } else if(index[0] == 0) {
                            dropLabel.setText(DROP_1);
                            oldFile[0] = (File) droppedFiles.get(0);
                            index[0]++;
                        } else {
                            newFile[0] = (File) droppedFiles.get(0);
                            evaluateToFile(oldFile[0], newFile[0]);
                            index[0] = 0;
                            dropLabel.setText(DROP_0);
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

    public static void evaluateToFile(File f1, File f2) throws IOException {
        String output = new CompareItem(load(f1), f1.getAbsolutePath()).compare(new CompareItem(load(f2), f2.getAbsolutePath()));

        String filename = "result-" + getTime() + ".txt";
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(output);
        }
        Desktop.getDesktop().edit(new File(filename));
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
