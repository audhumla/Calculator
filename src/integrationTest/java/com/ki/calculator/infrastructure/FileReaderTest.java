package com.ki.calculator.infrastructure;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileReaderTest {

    @Nested
    class HappyPath {
        @Test
        void readFileShouldReturnAListOfString(@TempDir Path tempDir) throws IOException {
            Path file = tempDir.resolve("tmp-test.txt");
            Files.write(file, Arrays.asList("line0", "line1", "line2", "line3"));

            List<String> actual = FileReader.readFile(file.toFile());

            assertThat(actual).containsExactly("line0", "line1", "line2", "line3");
        }
    }

    @Nested
    class UnhappyPath {
        @Test
        void shouldReturnAnEmptyListIfTheFileDoesNotExist(){
            List<String> actual = FileReader.readFile(new File("Not_existing_file"));

            assertThat(actual).isEmpty();
        }
    }
}