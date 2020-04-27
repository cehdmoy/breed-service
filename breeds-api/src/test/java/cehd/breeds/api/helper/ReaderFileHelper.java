package cehd.breeds.api.helper;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFileHelper {

    @SneakyThrows
    public String readFile(String fileName) {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        return data;
    }
}
