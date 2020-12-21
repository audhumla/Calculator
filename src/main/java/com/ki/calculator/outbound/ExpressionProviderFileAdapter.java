package com.ki.calculator.outbound;

import com.ki.calculator.domain.model.Expression;
import com.ki.calculator.domain.port.ExpressionProvider;
import com.ki.calculator.infrastructure.FileReader;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static com.ki.calculator.outbound.ExpressionMapper.convert;

public class ExpressionProviderFileAdapter implements ExpressionProvider {

    private final File file;

    public ExpressionProviderFileAdapter(String filepath) {
        this.file = new File(filepath);
        if (!file.exists())
            throw new IllegalArgumentException("File with name: \"" + filepath + "\" does not exist.");
    }

    @Override
    public Optional<Expression> find() {
        List<String> lines = FileReader.readFile(file);
        if (lines.isEmpty())
            return Optional.empty();

        try {
            return Optional.of(
                    convert(lines)
            );
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
