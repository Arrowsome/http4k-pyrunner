package me.arrowsome.pyrunner

import me.arrowsome.pyrunner.Result
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.RoutingHttpHandler

class ApiHandlers(
    private val apiLenses: ApiLenses,
    private val scriptRunnerService: ScriptRunnerService,
) {

    fun runPyScript(request: Request): Response {
        val scriptName = apiLenses.scriptNamePathLens.extract(request)
        val result = scriptRunnerService.runPyScript(scriptName)

        return when (result) {
            is Result.Success<*> -> Response(OK)
            is Result.Failure<*> -> Response(NOT_FOUND)
        }

    }

}