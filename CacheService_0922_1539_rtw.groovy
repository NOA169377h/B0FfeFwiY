// 代码生成时间: 2025-09-22 15:39:33
class CacheService {

    // The cache configuration
    def grailsCacheService
    def cacheName = 'defaultCache'
    def defaultExpiry = 3600 // in seconds (1 hour)

    // Use the constructor to initialize the cache
    CacheService() {
        this.grailsCacheService = new GrailsCacheService()
    }

    // Caches an object with a given key and expiry time
    def cacheObject(String key, Object value, long expiry = defaultExpiry) {
        try {
            // Cache the object with the given key and expiry
            grailsCacheService.put(cacheName, key, value, [ex: expiry])
        } catch (Exception e) {
            // Log and handle any exceptions that occur during caching
            log.error 'Error caching object: ' + e.message
            throw e
        }
    }

    // Retrieves a cached object by its key
    def getCachedObject(String key) {
        try {
            // Retrieve the object from the cache
            return grailsCacheService.get(cacheName, key)
        } catch (Exception e) {
            // Log and handle any exceptions that occur during retrieval
            log.error 'Error retrieving cached object: ' + e.message
            throw e
        }
    }

    // Invalidates a cached object by its key
    def invalidateObject(String key) {
        try {
            // Invalidate the cache entry with the given key
            grailsCacheService.evict(cacheName, key)
        } catch (Exception e) {
            // Log and handle any exceptions that occur during invalidation
            log.error 'Error invalidating cached object: ' + e.message
            throw e
        }
    }

    // Clears the entire cache
    def clearCache() {
        try {
            // Clear the entire cache
            grailsCacheService.clear()
        } catch (Exception e) {
            // Log and handle any exceptions that occur during cache clearing
            log.error 'Error clearing cache: ' + e.message
            throw e
        }
    }

    // Sets the name of the cache
    void setCacheName(String cacheName) {
        this.cacheName = cacheName
    }

    // Sets the default expiry time for the cache
    void setDefaultExpiry(long defaultExpiry) {
        this.defaultExpiry = defaultExpiry
    }

    // Returns the cache name
    String getCacheName() {
        return cacheName
    }

    // Returns the default expiry time
    long getDefaultExpiry() {
        return defaultExpiry
    }
}
