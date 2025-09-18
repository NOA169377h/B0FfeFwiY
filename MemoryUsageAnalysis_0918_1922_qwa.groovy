// 代码生成时间: 2025-09-18 19:22:13
package com.example.monitoring

import java.lang.management.ManagementFactory
import java.lang.management.MemoryMXBean
import java.lang.management.MemoryPoolMXBean
import java.lang.management.MemoryUsage
import groovy.transform.CompileStatic

/**
 * Class responsible for analyzing memory usage of the Java Virtual Machine.
 * It provides methods to retrieve memory metrics and calculate usage.
 */
@CompileStatic
class MemoryUsageAnalyzer {

    private MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean()
    private List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans()

    /**
     * Retrieves the total memory used by the JVM.
     *
     * @return The total memory used in bytes.
     */
    long getTotalMemoryUsed() {
        memoryMXBean.heapMemoryUsage.used
    }

    /**
     * Retrieves the maximum memory that can be used by the JVM.
     *
     * @return The maximum memory that can be used in bytes.
     */
    long getMaxMemory() {
        memoryMXBean.heapMemoryUsage.max
    }

    /**
     * Calculates the percentage of memory currently used.
     *
     * @return The percentage of memory used.
     */
    int getMemoryUsagePercentage() {
        if (memoryMXBean.heapMemoryUsage.max == 0) {
            return 0 // To avoid division by zero
        }
        return (int) ((getTotalMemoryUsed() as double / getMaxMemory()) * 100)
    }

    /**
     * Retrieves detailed memory usage for each memory pool.
     *
     * @return A list of memory usage details for each memory pool.
     */
    Map<String, MemoryUsage> getMemoryPoolUsageDetails() {
        memoryPoolMXBeans.collectEntries { MemoryPoolMXBean bean ->
            [bean.name, bean.usage]
        }
    }

    /**
     * Main method for running the memory usage analysis as a standalone application.
     *
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        try {
            MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer()
            println "Total Memory Used: ${analyzer.getTotalMemoryUsed()} bytes"
            println "Max Memory: ${analyzer.getMaxMemory()} bytes"
            println "Memory Usage Percentage: ${analyzer.getMemoryUsagePercentage()}%"
            println "Memory Pool Usage Details:"
            analyzer.getMemoryPoolUsageDetails().each { pool, usage ->
                println "Pool: ${pool}, Usage: ${usage.used} bytes, Max: ${usage.max} bytes, Committed: ${usage.committed} bytes"
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
