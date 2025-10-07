// 代码生成时间: 2025-10-07 23:03:51
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames = true)
@EqualsAndHashCode
class Matrix {
    List<List<Double>> data
    
    Matrix(List<List<Double>> data) {
        this.data = data
    }
}

class MatrixOperations {
    
    // Adds two matrices
    static Matrix addMatrices(Matrix a, Matrix b) {
        if (a.data[0].size() != b.data[0].size() || a.data.size() != b.data.size()) {
            throw new IllegalArgumentException('Matrices must be of the same size')
        }
        List<List<Double>> result = new Matrix(a.data.size(), a.data[0].size()).data.collect {
            [0]..withIndex { _, int i -> a.data[i] + b.data[i] }
        }
        return new Matrix(result)
    }
    
    // Subtracts two matrices
    static Matrix subtractMatrices(Matrix a, Matrix b) {
        if (a.data[0].size() != b.data[0].size() || a.data.size() != b.data.size()) {
            throw new IllegalArgumentException('Matrices must be of the same size')
        }
        List<List<Double>> result = new Matrix(a.data.size(), a.data[0].size()).data.collect {
            [0]..withIndex { _, int i -> a.data[i] - b.data[i] }
        }
        return new Matrix(result)
    }
    
    // Multiplies two matrices
    static Matrix multiplyMatrices(Matrix a, Matrix b) {
        if (a.data[0].size() != b.data.size()) {
            throw new IllegalArgumentException('The number of columns in the first matrix must be equal to the number of rows in the second matrix')
        }
        List<List<Double>> result = new Matrix(b.data.size(), a.data[0].size()).data.collect {
            [0]..withIndex { _, int i -> b.data[i].collect { j, Double value -> a.data[j].sum { k, Double val -> val * b.data[k][i] } } }
        }
        return new Matrix(result)
    }
    
    // Transposes a matrix
    static Matrix transposeMatrix(Matrix matrix) {
        List<List<Double>> result = new Matrix(matrix.data[0].size(), matrix.data.size()).data.collect {
            [0]..withIndex { _, int i -> matrix.data.collect { int j, Double val -> matrix.data[j][i] } }
        }
        return new Matrix(result)
    }
}

/*
 * Usage:
 * Matrix a = new Matrix([[1, 2], [3, 4]])
 * Matrix b = new Matrix([[5, 6], [7, 8]])
 * Matrix result = MatrixOperations.addMatrices(a, b)
 * println result
 */