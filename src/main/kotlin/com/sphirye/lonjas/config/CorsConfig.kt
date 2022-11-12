package com.sphirye.lonjas.config

import com.sphirye.lonjas.service.tool.Constants
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedMethod(HttpMethod.POST)
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        config.maxAge = 3600
        source.registerCorsConfiguration("/api/**", config)
        return CorsFilter(source)
    }

    @Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
    class CorsConfig : Filter {
        @Throws(IOException::class, ServletException::class)
        override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
            val response = res as HttpServletResponse
            val request = req as HttpServletRequest
            response.setHeader("Access-Control-Allow-Origin", URL_CORS_ORIGIN)
            response.setHeader("Access-Control-Allow-Methods", URL_CORS_METHODS)
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Content-Type")
            response.setHeader("Access-Control-Max-Age", "3600")
            response.setHeader("Access-Control-Expose-Headers", "X-Total-Count")
            if ("OPTIONS".equals(request.method, ignoreCase = true)) {
                response.status = HttpServletResponse.SC_OK
            } else {
                chain.doFilter(req, res)
            }
        }

        companion object {
            private const val URL_CORS_ORIGIN = "*"
            private const val URL_CORS_METHODS = "*"
        }
    }

}