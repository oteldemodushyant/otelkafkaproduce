package demo.dushyant.otelkafkaproduce.configs;

public record Topic (String name, int partitions,int retentionTime,int replicationFactor) {}
