package metrics;

public interface Processor<T> {
    void init();
    void process(T content);
    Metric getMetric();
}
