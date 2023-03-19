package me.arrowsome.pyrunner

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ApiHandlersTests {
    private lateinit var apiHandlers: ApiHandlers
    private lateinit var apiLenses: ApiLenses
    private lateinit var scriptRunnerService: ScriptRunnerService

    @BeforeEach
    fun setup() {
        scriptRunnerService = mockk()
        apiLenses = mockk()
        apiHandlers = ApiHandlers(
            apiLenses = apiLenses,
            scriptRunnerService = scriptRunnerService,
        )
    }

    @Test
    fun `a python script is run with success response`() {
        val request = Request(GET, "/api/scripts/{script_name}")
        every { scriptRunnerService.runPyScript(any()) } returns Result.Success(true)
        every { apiLenses.scriptNamePathLens.extract(any()) } returns "test.py"

        val response = apiHandlers.runPyScript(request)

        verify { scriptRunnerService.runPyScript(any()) }
        verify { apiLenses.scriptNamePathLens.extract(any()) }
        assertEquals(OK, response.status)
    }

    @Test
    fun `a non existing py scripts ends with not found response`() {
        val request = Request(GET, "/api/scripts/{script_name}")
        every { scriptRunnerService.runPyScript(any()) } returns Result.Failure("Not found!")
        every { apiLenses.scriptNamePathLens.extract(any()) } returns "test.py"

        val response = apiHandlers.runPyScript(request)

        verify { scriptRunnerService.runPyScript(any()) }
        verify { apiLenses.scriptNamePathLens.extract(any()) }
        assertEquals(NOT_FOUND, response.status)
    }

}