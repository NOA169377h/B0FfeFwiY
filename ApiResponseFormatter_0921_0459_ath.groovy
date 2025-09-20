// 代码生成时间: 2025-09-21 04:59:16
 * This class provides functionality to format API responses into a standardized structure.
 * It includes error handling and is designed to be easily maintained and extended.
 */

import groovy.transform.TypeChecked

@TypeChecked
class ApiResponseFormatter {

    // Method to format a successful API response
    static def formatSuccessResponse(data) {
        // Handle null data
        if (data == null) {
            // Return an error response if the data is null
            return formatErrorResponse('Data is null.')
        }

        // Return the formatted response
        return [
            status: 'success',
            data: data
        ]
    }

    // Method to format an error API response
    static def formatErrorResponse(message) {
        // Return the formatted error response
        return [
            status: 'error',
            message: message
        ]
    }

    // Method to format a paginated API response
    static def formatPaginatedResponse(data, pagination) {
        // Handle null data or pagination
        if (data == null || pagination == null) {
            // Return an error response if either data or pagination is null
            return formatErrorResponse('Data or pagination is null.')
        }

        // Return the formatted paginated response
        return [
            status: 'success',
            data: data,
            pagination: pagination
        ]
    }

    // Main method for testing the ApiResponseFormatter class
    static void main(String[] args) {
        try {
            // Example usage of the formatSuccessResponse method
            def successResponse = formatSuccessResponse([example: 'data'])
            println 'Success Response: ' + successResponse

            // Example usage of the formatErrorResponse method
            def errorResponse = formatErrorResponse('An error occurred.')
            println 'Error Response: ' + errorResponse

            // Example usage of the formatPaginatedResponse method
            def paginatedResponse = formatPaginatedResponse([1, 2, 3], [page: 1, size: 10, total: 100])
            println 'Paginated Response: ' + paginatedResponse

        } catch (Exception e) {
            // Handle any unexpected errors
            println 'An unexpected error occurred: ' + e.message
        }
    }
}
