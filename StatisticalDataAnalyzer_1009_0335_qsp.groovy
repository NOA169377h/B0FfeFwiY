// 代码生成时间: 2025-10-09 03:35:21
import groovy.transform.TypeChecked
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics

@TypeChecked
class StatisticalDataAnalyzer {

    /***
     * Analyzes a list of numbers and returns a summary of statistical data.
     *
     * @param dataList A list of numbers to analyze
     * @return A map containing statistical data such as mean, median, and standard deviation.
     * @throws IllegalArgumentException If the input list is null or empty.
     */
    def analyzeData(List<Double> dataList) {
        if (!dataList || dataList.isEmpty()) {
            throw new IllegalArgumentException('Data list must not be null or empty.')
        }

        DescriptiveStatistics stats = new DescriptiveStatistics()
        dataList.each { stats.addValue(it) }

        Map<String, Double> result = [
            mean    : stats.getMean(),
            median  : stats.getPercentile(50),
            standardDeviation : stats.getStandardDeviation()
        ]
        return result
    }

    /***
     * Calculates the z-score for a given data point.
     *
     * @param dataPoint The data point for which to calculate the z-score
     * @param dataList The list of numbers used to calculate the mean and standard deviation
     * @return The z-score of the data point
     * @throws IllegalArgumentException If the input list is null or empty.
     */
    def calculateZScore(double dataPoint, List<Double> dataList) {
        if (!dataList || dataList.isEmpty()) {
            throw new IllegalArgumentException('Data list must not be null or empty for z-score calculation.')
        }

        DescriptiveStatistics stats = new DescriptiveStatistics()
        dataList.each { stats.addValue(it) }

        return (dataPoint - stats.getMean()) / stats.getStandardDeviation()
    }
}
