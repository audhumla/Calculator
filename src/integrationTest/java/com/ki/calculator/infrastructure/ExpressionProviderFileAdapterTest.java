package com.ki.calculator.infrastructure;

import com.ki.calculator.domain.model.Decimal;
import com.ki.calculator.domain.model.Expression;
import com.ki.calculator.domain.model.MathOperation;
import com.ki.calculator.domain.model.RightOperation;
import com.ki.calculator.domain.port.ExpressionProvider;
import com.ki.calculator.outbound.ExpressionProviderFileAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpressionProviderFileAdapterTest {

    @TempDir
    static Path tmpDir;
    static Path file;

    @BeforeAll
    static void setUp() throws IOException {
        file = tmpDir.resolve("integration-test-tmp.txt");
        assertThat(file.toFile().createNewFile())
                .as("Cannot create file: " + file)
                .isTrue();
    }

    @Nested
    class FindTest {
        private final ExpressionProvider sut = new ExpressionProviderFileAdapter(file.toString());

        @Nested
        class HappyPath {

            @Test
            void findShouldReturnExpression() {
                arrangeLinesInFile("add 2", "multiply 3", "apply 3");

                Expression actual = sut.find().get();

                assertThat(actual).isEqualTo(
                        new Expression(3)
                                .append(
                                        List.of(
                                                new RightOperation(MathOperation.SUM, Decimal.of(2)),
                                                new RightOperation(MathOperation.MULTIPLICATION, Decimal.of(3))
                                        )
                                )
                );
            }
        }

        @Nested
        class UnhappyPath {

            @Test
            void shouldReturnAnEmptyOptionalIfTheFileIsEmpty() {
                arrangeLinesInFile("");

                var actual = sut.find();

                assertThat(actual).isEmpty();
            }

            @Test
            void shouldReturnAnEmptyOptionalIfTheFileDoesNotContainApply() {
                arrangeLinesInFile("add 2", "multiply 3");

                var actual = sut.find();

                assertThat(actual).isEmpty();
            }

            @Test
            void shouldReturnAnEmptyOptionalIfTheSecondArgumentInOneLineIsNotANumber() {
                arrangeLinesInFile("add 1", "multiply aaaaaaa");

                var actual = sut.find();

                assertThat(actual).isEmpty();
            }
        }
    }

    @Nested
    class InitializationExceptionTest {

        @Test
        void findShouldReturnExpression() {
            Assertions.assertThatThrownBy(
                    () -> new ExpressionProviderFileAdapter("not existing file")
            )
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("File with name: \"not existing file\" does not exist.");
        }
    }

    private void arrangeLinesInFile(String... lines) {
        try {
            Files.write(file, Arrays.asList(lines));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
