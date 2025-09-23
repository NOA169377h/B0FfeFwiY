// 代码生成时间: 2025-09-23 22:30:22
class InventoryManagementService {

    /**
     * Adds a new inventory item.
     *
     * @param item The inventory item to add.
     * @return The added inventory item or null if the item is invalid.
     */
    def addItem(InventoryItem item) {
        if (item == null || item.name == null || item.quantity <= 0) {
            // Handle the error case when item is invalid
            throw new IllegalArgumentException("Invalid inventory item. Item name and quantity must be provided.")
        }

        // Add the item to the inventory database
        item.save(flush: true)
        return item
    }

    /**
     * Removes an inventory item by its identifier.
     *
     * @param id The unique identifier of the item to remove.
     * @return The removed inventory item or null if not found.
     */
    def removeItem(Long id) {
        def item = InventoryItem.get(id)
        if (item == null) {
            // Handle the error case when item is not found
            throw new ItemNotFoundException("Inventory item with ID $id not found.")
        }

        // Remove the item from the inventory database
        item.delete(flush: true)
        return item
    }

    /**
     * Updates an existing inventory item.
     *
     * @param item The inventory item to update.
     * @return The updated inventory item or null if the item is invalid.
     */
    def updateItem(InventoryItem item) {
        if (item == null || item.id == null || item.name == null || item.quantity <= 0) {
            // Handle the error case when item is invalid
            throw new IllegalArgumentException("Invalid inventory item. Item ID, name, and quantity must be provided.")
        }

        // Update the item in the inventory database
        item.save(flush: true)
        return item
    }

    /**
     * Retrieves an inventory item by its identifier.
     *
     * @param id The unique identifier of the item to retrieve.
     * @return The inventory item or null if not found.
     */
    def getItem(Long id) {
        return InventoryItem.get(id)
    }
}

/**
 * Domain class for inventory items.
 */
class InventoryItem {
    String name
    Integer quantity
    static constraints = {
        name(blank: false, nullable: false)
        quantity(min: 1)
    }
}

/**
 * Custom exception class for item not found errors.
 */
class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(String message) {
        super(message)
    }
}