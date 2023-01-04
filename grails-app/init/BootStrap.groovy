import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { servletContext ->
        SpringSecurityUtils.clientRegisterFilter("corsFilter",
            SecurityFilterPosition.SECURITY_CONTEXT_FILTER.order - 1 )
    }

    def destroy = {
    }
}