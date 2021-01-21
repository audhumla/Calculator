package com.ki.calculator.inbound;

import com.ki.calculator.domain.model.Decimal;
import com.ki.calculator.domain.model.FormatInfo;
import com.ki.calculator.domain.model.common.Either;
import com.ki.calculator.domain.usecase.CalculatorUseCase;
import com.ki.calculator.outbound.ExpressionProviderFileAdapter;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        if (!isValidInput(args))
            return;

        new CalculatorUseCase(
                new ExpressionProviderFileAdapter(args[0]),
                Presenter::print
        ).execute();
    }

    private static boolean isValidInput(String[] args) {
        if (args.length != 1) {
            printUsage();
            return false;
        }
        if (!Files.exists(Path.of(args[0]))) {
            System.out.println("File with name \""+ args[0] + "\" does not exist. Please, provide a valid file.");
            return false;
        }
        return true;
    }

    private static void printUsage() {
        System.out.println(
                "Provide as argument the file you want to parse. " +
                        "\nRead the README.md file to know more."
        );
        System.out.println(
                "Example: ./gradlew run input-file.txt"
        );
    }

    private static class Presenter {
        private static void print(Either<Exception, Decimal> result) {
            if (result.isLeft()) {
                System.out.println("Cannot perform the computation because of: " + result.left());
            } else {
                String output = result.right().format(Format.UNIT);
                System.out.println(output);
            }
        }
    }

    public enum Format implements FormatInfo {

        SIMPLE("#,##0.00"),
        UNIT("#,##0"),
        ;

        private final String pattern;

        private Format(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public String pattern() {
            return pattern;
        }
    }
}


