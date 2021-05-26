package org.leafbook.utils.tools;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;

@Data
@Component
public class IdGeneratorTools {
    @Value("${snowflake.datacenterId}")
    private long datacenterId;
    @Value("${snowflake.workId}")
    private long workId;

    private long workIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long sequenceBits = 12L;

    private long maxWorkId = ~(-1L << workIdBits);
    private long maxDatacenterId = ~(-1L << datacenterIdBits);

    private long twepoch = 1585644268888L;

    private long sequence = 0L;

    private long workIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workIdBits;
    private long timestampLeftShift = sequenceBits + workIdBits + datacenterIdBits;
    private long sequenceMask = ~(-1L << sequenceBits);

    private long lastTimestamp = -1L;

    private long getTimestamp() {
        return System.currentTimeMillis();
    }

    private static IdGeneratorTools idGenerator;
    static {
        idGenerator = new IdGeneratorTools();
    }

    public IdGeneratorTools() {
        if (workId > maxWorkId || workId < 0) {
            throw new IllegalArgumentException("workId param exception");
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("workId param exception");
        }
    }

    public static long nextId() {
        return idGenerator.getNextId();
    }

    private synchronized long getNextId() {
        long timestamp = getTimestamp();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("clock exception");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift | (datacenterId << datacenterIdShift) | (workId << workIdShift) | sequence);
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = getTimestamp();

        while (timestamp <= lastTimestamp) {
            timestamp = getTimestamp();
        }
        return timestamp;
    }
}











