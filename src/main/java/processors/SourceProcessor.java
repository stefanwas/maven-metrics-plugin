package processors;

import model.Source;
import model.SourceInfo;

public abstract class SourceProcessor<T> {
    public void init() {};
    public abstract SourceInfo<T> process(Source<T> source);
}
