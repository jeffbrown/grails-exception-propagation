class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'test', 'action': 'test1', method: 'GET')

        "/test1"(controller: 'test', 'action': 'test1', method: 'GET')
        "/test2"(controller: 'test', 'action': 'test2', method: 'GET')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
