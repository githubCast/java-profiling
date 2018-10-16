package com.acme.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IODemo {
    public static void main(String[] args) {
//            Files.lines(Paths.get("test.txt"))
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                            new BufferedInputStream(
                                new FileInputStream(
                                    new File("test.txt"))),
                        "cp866"), 8192)
        ) {


            String readLine = null;
            while ((readLine = reader.readLine()) != null) {
                System.out.println(">>> " + readLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
