package io.github.ardonplay.javatronilizer;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
       Main instance
                = new Main();

        InputStream is = instance.getFileAsIOStream("test.html");
        instance.printFileContent(is);
    }
    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private void printFileContent(InputStream is) throws IOException
    {
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);)
        {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            is.close();
        }
    }
}