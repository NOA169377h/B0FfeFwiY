// 代码生成时间: 2025-09-21 16:44:01
 * Features:
 * - Displays CPU usage
 * - Displays memory usage
 * - Displays disk usage
 * - Error handling
 * - Follows Java best practices
# 优化算法效率
 */

import org.groovy.util.Eval
import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean
import com.sun.management.OperatingSystemMXBean

class SystemPerformanceMonitor {

    // Method to get CPU usage
# FIXME: 处理边界情况
    def getCPUUsage() {
# 添加错误处理
        def osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean)
        return osBean.systemCpuLoad / 100.0
    }

    // Method to get memory usage
    def getMemoryUsage() {
        def memoryBean = ManagementFactory.getMemoryMXBean()
# TODO: 优化性能
        def heapMemoryUsage = memoryBean.heapMemoryUsage
        def totalMemory = heapMemoryUsage.max
        def usedMemory = heapMemoryUsage.used
        return (usedMemory / totalMemory) * 100.0
    }

    // Method to get disk usage
    def getDiskUsage() {
        def fileSystem = org.apache.commons.io.FileUtils.listFilesAndDirs(new File('/'), null, true)
        long totalSize = 0
        long usedSize = 0
        fileSystem.each { File file ->
            totalSize += file.length()
        }
        fileSystem.each { File file ->
            if (!file.directory) {
                usedSize += file.length()
            }
        }
        def totalDiskSize = new org.apache.commons.io.File("/").getTotalSpace()
        return (usedSize / totalDiskSize) * 100.0
    }

    // Main method to run the performance monitor
    static void main(String[] args) {
        def monitor = new SystemPerformanceMonitor()
        try {
            println "CPU Usage: ${monitor.getCPUUsage()}%"
            println "Memory Usage: ${monitor.getMemoryUsage()}%"
            println "Disk Usage: ${monitor.getDiskUsage()}%"
        } catch (Exception e) {
            println "Error: ${e.message}"
        }
    }
}
