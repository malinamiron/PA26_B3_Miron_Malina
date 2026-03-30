package org.example.homework;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.example.compulsory.Repository;
import org.example.compulsory.Resource;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.List;

public class Report implements Command {
    private Report(){};

    public static void getReport(Repository repository) throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
//        cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
        cfg.setClassForTemplateLoading(Report.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        List<Resource> resources = repository.getRepository().stream().toList();
        Map<String, Object> root = new HashMap<>();
        root.put("items", resources);


        Template temp = cfg.getTemplate("report.ftlh");
        File outputFile = new File("report.html");

        try (Writer out = new FileWriter(outputFile)) {
            temp.process(root, out);
        }

        try {
            Desktop desk = Desktop.getDesktop();
            desk.browse(outputFile.toURI());

        } catch (IOException e) {
            System.err.println("Failed to open the file: " + e.getMessage());
        }

    }
}
