package io.reader;

import model.Source;

public interface SourceReader<T> {
    Source<T> readSource(String location);
}
