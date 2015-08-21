package processors;

import model.Source;
import model.SourceMetrics;

public abstract class SourceProcessor<T> {
    public void init() {};
    public abstract SourceMetrics<T> process(Source<T> source);
}
