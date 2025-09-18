// 代码生成时间: 2025-09-18 10:02:11
class FileBackupSync {

    // Define the source and destination directories
    private String sourceDirectory
    private String destinationDirectory

    // Constructor
    FileBackupSync(String source, String destination) {
        this.sourceDirectory = source
        this.destinationDirectory = destination
    }

    /**
     * Synchronizes the files from the source directory to the destination directory.
     * It only copies the files that do not exist in the destination or are newer in the source.
     *
     * @param syncType The type of synchronization to perform.
     */
    def syncFiles(String syncType = 'full') {
        try {
            // Check the directories
            if (!new File(sourceDirectory).exists() || !new File(destinationDirectory).exists()) {
                println 'Source or destination directory does not exist.'
                return
            }

            // Perform synchronization based on the sync type
            switch (syncType) {
                case 'full':
                    fullSync()
                    break
                case 'incremental':
                    incrementalSync()
                    break
                default:
                    println 'Invalid sync type.'
                    break
            }
        } catch (Exception e) {
            // Error handling
            println 'An error occurred during synchronization: ' + e.message
        }
    }

    /**
     * Performs a full synchronization by copying all files from source to destination.
     */
    private void fullSync() {
        // Get all files in the source directory
        File sourceDir = new File(sourceDirectory)
        File[] files = sourceDir.listFiles()
        if (files) {
            for (File file in files) {
                // Create the destination directory if it doesn't exist
                File destFile = new File(destinationDirectory + '/' + file.name)
                if (!destFile.parentFile.exists()) {
                    destFile.parentFile.mkdirs()
                }
                // Copy the file
                fileInputStream = new FileInputStream(file)
                fileOutputStream = new FileOutputStream(destFile)
                fileInputStream.transferTo(fileOutputStream)
                fileInputStream.close()
                fileOutputStream.close()
            }
        }
        println 'Full synchronization completed.'
    }

    /**
     * Performs an incremental synchronization by copying only new or updated files.
     */
    private void incrementalSync() {
        // Get all files in the source and destination directories
        File sourceDir = new File(sourceDirectory)
        File destinationDir = new File(destinationDirectory)
        File[] sourceFiles = sourceDir.listFiles()
        File[] destinationFiles = destinationDir.listFiles()
        if (sourceFiles) {
            for (File file in sourceFiles) {
                // Find the corresponding file in the destination
                File destFile = new File(destinationDirectory + '/' + file.name)
                if (!destFile.exists() || file.lastModified() > destFile.lastModified()) {
                    // Copy the file if it's new or updated
                    fileInputStream = new FileInputStream(file)
                    fileOutputStream = new FileOutputStream(destFile)
                    fileInputStream.transferTo(fileOutputStream)
                    fileInputStream.close()
                    fileOutputStream.close()
                }
            }
        }
        println 'Incremental synchronization completed.'
    }

    static void main(String[] args) {
        // Example usage
        FileBackupSync backupSync = new FileBackupSync('/path/to/source', '/path/to/destination')
        backupSync.syncFiles('full')
    }
}
