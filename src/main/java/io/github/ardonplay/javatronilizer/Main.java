package io.github.ardonplay.javatronilizer;

import java.io.*;
import io.github.ardonplay.javatronilizer.Model.DefaultModel;
import io.github.ardonplay.javatronilizer.Parser.FieldPattern;

import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws IOException {
       Main instance = new Main();

        InputStream is = instance.getFileAsIOStream("test.html");

        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr))
        {
            StringBuilder st = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
               st.append(line);
            }
            is.close();
            FieldPattern fieldPattern = new FieldPattern(new DefaultModel());
            Matcher matcher = fieldPattern.matcher(st.toString());

            while (matcher.find())  {
                System.out.println(st.substring(matcher.start()+1, matcher.end()-1));
            }

        }
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
}