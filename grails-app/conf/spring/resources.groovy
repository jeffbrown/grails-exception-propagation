import test.filters.CorsFilter
import org.springframework.web.servlet.i18n.SessionLocaleResolver

// Place your Spring DSL code here
beans = {
    corsFilter(CorsFilter)
    localeResolver(SessionLocaleResolver) {
        Locale.setDefault(new Locale("pt","BR"))
    }
    "defaultGrailsjava.lang.DoubleConverter"(DoubleConverter)
    "defaultGrailsjava.lang.BigDecimalConverter"(BigDecimalConverter)
}