// 代码生成时间: 2025-09-22 21:54:09
// 文件名：XssProtectionService.groovy
// 描述：使用Grails框架实现的XSS攻击防护服务

import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.web.util.HtmlUtils

class XssProtectionService {
    
    // 功能：清理输入以防止XSS攻击
    // 输入：String类型的用户输入
    // 输出：清理后的字符串，防止XSS攻击
    String sanitizeInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException('Input cannot be null')
        }
        
        // 使用HtmlUtils.htmlEscape()进行HTML转义，防止XSS
        return HtmlUtils.htmlEscape(input)
    }
    
    // 功能：设置HTTP响应头以防护XSS攻击
    // 输入：HttpServletResponse响应对象
    // 无输出
    void setHttpHeaders(HttpServletResponse response) {
        try {
            // 设置X-XSS-Protection响应头为1; mode=block
            response.setHeader(HttpHeaders.X_XSS_PROTECTION, '1; mode=block')
            // 设置Content-Security-Policy响应头
            // 这里需要根据实际应用的需要来配置CSP策略
            response.setHeader(HttpHeaders.CONTENT_SECURITY_POLICY, "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; img-src 'self' data:; style-src 'self' 'unsafe-inline';")
        } catch (Exception e) {
            // 错误处理
            log.error('Failed to set HTTP headers for XSS protection', e)
        }
    }
    
    // 日志对象
    private static final Log log = LogFactory.getLog(XssProtectionService)
}

// 使用示例
// XssProtectionService xssService = new XssProtectionService()
// String userInput = "<script>alert('xss')</script>"
// String sanitizedInput = xssService.sanitizeInput(userInput)
// println sanitizedInput
// HttpServletResponse response = ... // 从请求中获取response对象
// xssService.setHttpHeaders(response)