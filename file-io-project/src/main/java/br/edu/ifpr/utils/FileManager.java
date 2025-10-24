package br.edu.ifpr.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    public static void writter(String content,Path path)throws IOException{
        Files.createDirectories(path.getParent());
        Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    public static void append(String content,Path path)throws IOException{
        Files.createDirectories(path.getParent());
        Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
    public static List<String> readAll(Path path)throws IOException{
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
    public static void replaceText(Path path, String target, String replacement)throws IOException{
        Path temp = Files.createTempFile("tempfile", ".txt");
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            BufferedWriter writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)){
                String line;
                while((line = reader.readLine()) != null){
                    line = line.replace(target, replacement);
                    writer.write(line);
                    writer.newLine();
                }
        }
    }
}
