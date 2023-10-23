package io.github.ardonplay.javatronilizer;

import java.io.*;
import io.github.ardonplay.javatronilizer.models.DefaultModel;
import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.parser.patterns.FieldPattern;
import io.github.ardonplay.javatronilizer.parser.patterns.ListPattern;


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
            User user = new User("Александр я ебался в жопу блять ");
            Model model = new DefaultModel();
            model.addAttribute("title", "ебобошка");
            model.addAttribute("user",user );
            FieldPattern pattern = new FieldPattern(model);
            ListPattern listPattern = new ListPattern(model);
            listPattern.transform(st.toString());
            //System.out.println(pattern.transform(st.toString()));

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