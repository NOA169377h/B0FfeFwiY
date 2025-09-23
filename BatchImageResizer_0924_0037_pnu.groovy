// 代码生成时间: 2025-09-24 00:37:33
import grails.transaction.Transactional
import groovy.io.FileType
import groovy.util.logging.Log
import org.grails.io.support.Resource
import org.springframework.web.multipart.commons.CommonsMultipartFile

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException

@Log
@Transactional
class BatchImageResizer {

    def imageService

    /**
     * Resizes a list of images to the specified dimensions.
     * @param images A list of CommonsMultipartFile objects to resize
     * @param targetWidth The target width for the images
     * @param targetHeight The target height for the images
     * @return A list of resized File objects
     */
    List<File> resizeImages(List<CommonsMultipartFile> images, int targetWidth, int targetHeight) {
        List<File> resizedFiles = new ArrayList<>()

        images.each { image ->
            resizedFiles.addAll(resizeImage(image, targetWidth, targetHeight))
        }

        return resizedFiles
    }

    /**
     * Resizes a single image to the specified dimensions.
     * @param image The image to resize
     * @param targetWidth The target width for the image
     * @param targetHeight The target height for the image
     * @return A list containing the resized File object
     */
    List<File> resizeImage(CommonsMultipartFile image, int targetWidth, int targetHeight) {
        List<File> resizedFiles = new ArrayList<>()

        try {
            // Check if the image is not empty
            if (image.empty) {
                log.error "Image is empty."
                throw new IllegalArgumentException("Image is empty.")
            }

            // Create a buffered image from the file
            BufferedImage originalImage = ImageIO.read(image.inputStream)

            // Create a new image with the target dimensions
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.type)

            // Draw the original image on the new image, scaling it to fit the new size
            resizedImage.getGraphics().drawImage(originalImage, 0, 0, targetWidth, targetHeight, null)

            // Create a temporary file to store the resized image
            File tempFile = File.createTempFile("resized", ".${image.originalFilename.tokenize('.').last()}")
            tempFile.deleteOnExit()

            // Write the resized image to the temporary file
            ImageIO.write(resizedImage, image.originalFilename.tokenize('.').last(), tempFile)

            // Add the resized file to the list
            resizedFiles << tempFile
        } catch (IOException e) {
            log.error "Failed to resize image: ${e.message}"
        }

        return resizedFiles
    }
}
