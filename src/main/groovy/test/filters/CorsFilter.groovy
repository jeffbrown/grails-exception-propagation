package test.filters

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

class CorsFilter extends org.springframework.web.filter.CorsFilter {

    CorsFilter() {
        super(configurationSource())
    }

    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true)
        config.addAllowedOrigin('*')
        ['origin', 'authorization', 'x-auth-token', 'accept', 'content-type', 'x-requested-with'].each { header ->
            config.addAllowedHeader(header)
        }
        ['GET', 'HEAD', 'POST', 'PUT', 'DELETE', 'OPTIONS'].each { method ->
            config.addAllowedMethod(method)
        }
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration('/**', config)
        return source
    }
}
