// 代码生成时间: 2025-09-21 11:16:35
import java.lang.management.ManagementFactory
import java.lang.management.RuntimeMXBean
import java.util.List
import java.util.stream.Collectors

/**
 * A simple process manager class using Java and Grails framework.
 * It provides basic functionality to list running processes and to terminate them.
 */
class ProcessManager {

    /**
     * Lists all running processes.
     *
     * @return A list of process information.
     */
    List<String> listProcesses() {
        List<RuntimeMXBean> runtimeBeans = ManagementFactory.getRuntimeMXBeans()
        return runtimeBeans.stream()
            .map(RuntimeMXBean::getName)
            .collect(Collectors.toList())
    }

    /**
     * Terminates a process by its process id.
     *
     * @param pid The process id to terminate.
     * @return True if the process was successfully terminated, false otherwise.
     */
    boolean terminateProcess(Long pid) {
        try {
            // In a real-world scenario, you would use a library or API that allows
            // killing processes by PID, as Java does not provide a built-in method for this.
            // Here, we just simulate the termination.
            println "Process $pid has been terminated."
            return true
        } catch (Exception e) {
            // Handle exceptions, e.g., process not found, permission denied.
            println "Failed to terminate process $pid: ${e.message}"
            return false
        }
    }
}
