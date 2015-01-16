package com.lhx.util;

import java.io.*;

/**
 * Created by xin on 14-4-14.
 */
public class WriteLines {
    private File file ;
    public WriteLines() {
    }
    public WriteLines(File file) {
        this.file = file ;
    }
    public void WriteLine(String[] strs) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        for (String arr : strs) {
            bw.write(arr + "\t\n");
        }
        bw.close();
        fileWriter.close();
    }
    public void WriteLineByEncode(String[] strs, String encode) throws IOException {
        OutputStream out = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(out,encode);
        BufferedWriter bw = new BufferedWriter(osw);
        for (String arr : strs) {
            bw.write(arr + "\t\n");
        }
        bw.close();
        osw.close();
        out.close();
    }
}
