import ratpack.groovy.template.TextTemplateModule

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module(TextTemplateModule)
    }

    serverConfig {
        development(true)
    }

    handlers {
        get {
            def bundle = "/"
            render groovyTemplate([bundle: bundle], "index.html")
        }

        get("dev") {
            def bundle = "http://localhost:3000/static/"
            render groovyTemplate([bundle: bundle], "index.html")
        }

        get('static/:id') {
            render "http://localhost:3000/static/${context.pathTokens['id']}".toURL().text
        }

        files {
            dir "react"
        }
    }
}
