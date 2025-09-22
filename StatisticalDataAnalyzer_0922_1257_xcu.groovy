// 代码生成时间: 2025-09-22 12:57:11
import groovy.transform.CompileStatic
# 改进用户体验

/**
 * StatisticalDataAnalyzer is a class designed to perform statistical analysis on data.
 * It follows Java best practices for code structure, error handling,
 * documentation, and maintainability.
 */
# FIXME: 处理边界情况
@CompileStatic
class StatisticalDataAnalyzer {

    /**
     * This method calculates the mean of the provided data.
     * @param data An array of numbers to calculate the mean.
     * @return The mean of the data, or null if data is invalid.
# 改进用户体验
     */
    Double calculateMean(List<Double> data) {
# 扩展功能模块
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.")
# NOTE: 重要实现细节
        }

        double sum = data.sum()
        return sum / data.size()
    }

    /**
     * This method calculates the median of the provided data.
     * @param data An array of numbers to calculate the median.
     * @return The median of the data, or null if data is invalid.
     */
# 添加错误处理
    Double calculateMedian(List<Double> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.")
# 添加错误处理
        }

        List<Double> sortedData = data.sort()
        int mid = sortedData.size() / 2
# 改进用户体验
        if (sortedData.size() % 2 == 0) {
            // Even number of elements, take the average of the two middle numbers.
            return (sortedData[mid - 1] + sortedData[mid]) / 2
# TODO: 优化性能
        } else {
            // Odd number of elements, take the middle number.
            return sortedData[mid]
        }
    }

    /**
     * This method calculates the standard deviation of the provided data.
     * @param data An array of numbers to calculate the standard deviation.
     * @return The standard deviation of the data, or null if data is invalid.
     */
# NOTE: 重要实现细节
    Double calculateStandardDeviation(List<Double> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.")
        }
# 改进用户体验

        double mean = calculateMean(data)
        double sumOfSquares = data.collect { (it - mean) ** 2 }.sum()
        return Math.sqrt(sumOfSquares / (data.size() - 1))
    }

    /**
     * Main method for testing the StatisticalDataAnalyzer.
     * @param args Command line arguments.
     */
    static void main(String[] args) {
        StatisticalDataAnalyzer analyzer = new StatisticalDataAnalyzer()

        List<Double> data = [4.2, 7.1, 5.8, 3.3, 6.1]
# 增强安全性
        println "Mean: \${analyzer.calculateMean(data)}"
        println "Median: \${analyzer.calculateMedian(data)}"
        println "Standard Deviation: \${analyzer.calculateStandardDeviation(data)}"
    }
}
