// 代码生成时间: 2025-09-22 09:08:45
package apiTool

import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

/**
 * ApiResponseFormatter is a utility class that formats API responses.
 * It provides methods to handle common API response scenarios,
 * including success and error responses.
 */
class ApiResponseFormatter {

    /**
     * Format a successful API response.
     *
     * @param data The data to be included in the response.
     * @return A formatted JSON string representing a successful response.
     */
    static String formatSuccessResponse(def data) {
        def response = [
            status: 'success',
            data: data
        ]
        return new JsonBuilder(response).toPrettyString()
    }

    /**
     * Format an error API response.
     *
     * @param errorMessage The error message to be included in the response.
     * @param errorCode An optional error code that describes the error.
     * @return A formatted JSON string representing an error response.
     */
    static String formatErrorResponse(String errorMessage, Integer errorCode = null) {
        def response = [
            status: 'error',
            message: errorMessage
        ]
        if (errorCode) {
            response.code = errorCode
        }
        return new JsonBuilder(response).toPrettyString()
    }

    /**
     * Parse a JSON string into a Map.
     *
     * @param json The JSON string to parse.
     * @return A Map representing the parsed JSON.
     * @throws IllegalArgumentException if the JSON is invalid.
     */
    static Map parseJson(String json) {
        try {
            new JsonSlurper().parseText(json)
        } catch (Exception e) {
            throw new IllegalArgumentException('Invalid JSON format', e)
        }
    }
}
