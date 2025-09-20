// 代码生成时间: 2025-09-20 16:42:46
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

// JsonDataTransformer class which provides functionality to transform JSON data
class JsonDataTransformer {

    // Method to convert JSON string to a Map object
    Map<String, Object> convertJsonToMap(String jsonString) {
        try {
            // Use JsonSlurper to parse JSON string
            JsonSlurper slurper = new JsonSlurper()
            Map<String, Object> map = slurper.parseText(jsonString)
            return map
        } catch (Exception e) {
            // Handle parsing error
            println "Error parsing JSON: \${e.message}"
            return null
        }
    }

    // Method to convert a Map object to a JSON string
    String convertMapToJson(Map<String, Object> map) {
        try {
            // Use JsonOutput to convert Map to JSON string
            String jsonString = JsonOutput.toJson(map)
            return jsonString
        } catch (Exception e) {
            // Handle serialization error
            println "Error serializing Map to JSON: \${e.message}"
            return null
        }
    }

    // Main method to demonstrate usage of the JsonDataTransformer
    static void main(String[] args) {
        // Example JSON string
        String jsonInput = "{"name":"John", "age":30}"

        // Create an instance of JsonDataTransformer
        JsonDataTransformer transformer = new JsonDataTransformer()

        // Convert JSON string to Map
        Map<String, Object> map = transformer.convertJsonToMap(jsonInput)
        if (map) {
            println "Converted Map: \${map}"

            // Convert Map back to JSON string
            String jsonOutput = transformer.convertMapToJson(map)
            println "Converted JSON: \${jsonOutput}"
        }
    }
}
