package io.github.ardonplay.javatronilizer;

<<<<<<< HEAD
import java.io.*;
=======
import io.github.ardonplay.javatronilizer.Model.DefaultModel;
import io.github.ardonplay.javatronilizer.Parser.FieldPattern;

import java.io.*;
import java.util.regex.Matcher;

>>>>>>> 0832a93 (dev: initial commit)
public class Main {
    public static void main(String[] args) throws IOException {
       Main instance
                = new Main();

        InputStream is = instance.getFileAsIOStream("test.html");
<<<<<<< HEAD
        instance.printFileContent(is);
=======

        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);)
        {
            StringBuilder st = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
               st.append(line);
            }
            is.close();
            FieldPattern fieldPattern = new FieldPattern(new DefaultModel());
            Matcher matcher = fieldPattern.matcher(st.toString());

            while (matcher.find()) {
                System.out.println(st.substring(matcher.start()+1, matcher.end()-1));
            }

        }
>>>>>>> 0832a93 (dev: initial commit)
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
<<<<<<< HEAD

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
=======
>>>>>>> 0832a93 (dev: initial commit)
}