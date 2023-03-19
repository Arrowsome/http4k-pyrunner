package me.arrowsome.pyrunner

import org.http4k.core.Method.GET
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.kodein.di.instance


fun main() {
    DebuggingFilters
        .PrintRequestAndResponse()
        .then(backend)
        .asServer(Jetty())
        .start()
}

val backend: RoutingHttpHandler
    get() {
        val apiHandlers by di.instance<ApiHandlers>()

        return routes(
            "/api/scripts/{script_name}" bind GET to apiHandlers::runPyScript
        )
    }