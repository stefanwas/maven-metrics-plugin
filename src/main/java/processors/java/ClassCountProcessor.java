package processors.java;

import model.Source;
import model.SourceInfo;
import processors.SourceProcessor;

import java.util.HashMap;
import java.util.Map;

public class ClassCountProcessor extends SourceProcessor<String> {

    @Override
    public JavaClassMetrics process(Source<String> source) {
        //TODO
        JavaClassMetrics metrics = new JavaClassMetrics();
        return metrics;
    }

    public static class JavaClassMetrics extends SourceInfo<String> {

        public static final String METRIC_NAME = "JAVA CLASS COUNTER";

        private int allClasses = 0;
        private int allInterfaces = 0;
        private int allEnums = 0;

        public JavaClassMetrics() {
            this.name = METRIC_NAME;
            this.description = "Java classes, interfaces, enuums, etc.";
        }

        public int getAllClasses() {
            return allClasses;
        }

        public void setAllClasses(int allClasses) {
            this.allClasses = allClasses;
        }

        public int getAllInterfaces() {
            return allInterfaces;
        }

        public void setAllInterfaces(int allInterfaces) {
            this.allInterfaces = allInterfaces;
        }

        public int getAllEnums() {
            return allEnums;
        }

        public void setAllEnums(int allEnums) {
            this.allEnums = allEnums;
        }

        @Override
        public Map<String, String> getMetricsMap() {
            Map<String, String> metrics = new HashMap<>();
            metrics.put("total classes", String.valueOf(allClasses));
            metrics.put("total interfaces", String.valueOf(allInterfaces));
            metrics.put("total enums", String.valueOf(allEnums));
            return metrics;
        }
    }
}
