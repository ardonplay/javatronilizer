package io.github.ardonplay.javatronilizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
               st.append(System.getProperty("line.separator"));
            }
            is.close();
            User user = new User("Не ебу как оно работает, но оно блять работает");

            List<User> users = new ArrayList<>();
            users.add(new User("абоба"));
            users.add(new User("лупа"));

            Model model = new DefaultModel();
            model.addAttribute("title", "ебобошка");
            model.addAttribute("user",user);
            model.addAttribute("users", users);

            FieldPattern pattern = new FieldPattern(model);
            ListPattern listPattern = new ListPattern(model);

            System.out.println(pattern.transform(listPattern.transform(st.toString())));

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