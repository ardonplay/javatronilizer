package io.github.ardonplay.javatronilizer;

import io.github.ardonplay.javatronilizer.models.DefaultModel;
import io.github.ardonplay.javatronilizer.models.Model;
import io.github.ardonplay.javatronilizer.templater.Templater;

public class TestClass {

    public static void main(String[] args)  {
        String testHtml = """
                <html>
                    <head>
                      <title>{title}</title>
                    </head>
                    <body>
                    <img src="static/solyambus.gif" alt="solyambus">

                    {help message1}
                    </body>
                </html>""";
        Model model = new DefaultModel();
        model.addAttribute("title", "test title");
        model.addAttribute("help message", "This is some message in html");

        Templater templater = new Templater(testHtml, model);
        try {
            System.out.println(templater.transform());
        }catch (AttributeNotFoundException e){
            System.out.println("Something happened");
        }
    }
}
