// 代码生成时间: 2025-10-14 00:00:21
 * This class represents a customer service robot that can interact with customers via text messages.
 * It demonstrates basic error handling, comments, and follows Java best practices for maintainability and extensibility.
 */
class CustomerServiceRobot {

    /**
     * Greet the customer and provide options.
     *
     * @param customerName The name of the customer.
     * @return A greeting message with options.
     */
    String greet(String customerName) {
        return "Hello, ${customerName}! How can I assist you today?
" +
               "1. Check order status
" +
               "2. Request a refund
" +
               "3. Contact support
" +
               "4. Exit";
    }

    /**
     * Process the customer's choice.
     *
     * @param choice The customer's selected option.
     * @return A response based on the customer's choice.
     */
    String processChoice(int choice) {
        switch (choice) {
            case 1:
                return checkOrderStatus();
            case 2:
                return requestRefund();
            case 3:
                return contactSupport();
            case 4:
                return "Thank you for using our service. Goodbye!";
            default:
                return "Invalid choice. Please select a valid option.";
        }
    }

    private String checkOrderStatus() {
        // Simulate checking order status
        return "Order status checked successfully.";
    }

    private String requestRefund() {
        // Simulate requesting a refund
        return "Refund request processed.";
    }

    private String contactSupport() {
        // Simulate contacting support
        return "You have been connected to our support team.";
    }

    public static void main(String[] args) {
        def robot = new CustomerServiceRobot();
        def customerName = "John Doe";
        println robot.greet(customerName);

        while (true) {
            def input = System.console().readLine("Enter your choice (1-4): ")
            int choice;
            try {
                choice = Integer.parseInt(input);
                println robot.processChoice(choice);
            } catch (NumberFormatException e) {
                println "Invalid input. Please enter a number between 1 and 4.";
            }
        }
    }
}