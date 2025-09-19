// 代码生成时间: 2025-09-19 18:28:22
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.apache.http.HttpHeaders
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

import java.nio.charset.StandardCharsets

/**
 * WebContentFetcher class is a simple web content fetching tool that retrieves HTML content from a given URL.
 * It uses the GRAILS framework and Apache HttpClient for HTTP operations.
 */
class WebContentFetcher {

    /**
     * The main method that fetches the web content from the provided URL.
     *
     * @param targetUrl The URL from which to fetch the content
     * @return A String containing the fetched HTML content or an error message.
     */
    static String fetchWebContent(String targetUrl) {
        try {
            // Create an HttpClient instance
            CloseableHttpClient httpClient = HttpClients.createDefault()
            // Create a HttpGet request with the target URL
            HttpGet request = new HttpGet(targetUrl)
            // Add the 'User-Agent' header to the request
            request.setHeader(HttpHeaders.USER_AGENT, 'WebContentFetcher/1.0')
            
            // Execute the request and get the response
            HttpResponse response = httpClient.execute(request)
            
            // Check if the response status is OK (200)
            if (response.getStatusLine().getStatusCode() == 200) {
                // Get the response content as a String
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)
            } else {
                // Return an error message if the status is not OK
                return "Error: Unable to fetch content. HTTP Status: ${response.getStatusLine().getStatusCode()}"
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the fetch process
            return "Error: An exception occurred while fetching content - ${e.message}"
        } finally {
            // Close the HttpClient instance to release system resources
            httpClient.close()
        }
    }

    /**
     * Entry point for the program.
     * It fetches the content from a specified URL and prints it to the console.
     *
     * @param args Command line arguments where the first argument is the URL to fetch.
     */
    static void main(String[] args) {
        if (args.length != 1) {
            println 'Usage: WebContentFetcher <URL>'
            return
        }
        
        String content = fetchWebContent(args[0])
        println content
    }
}
