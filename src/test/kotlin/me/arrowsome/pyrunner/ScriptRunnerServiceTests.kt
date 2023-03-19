package me.arrowsome.pyrunner

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScriptRunnerServiceTests {
    private lateinit var scriptRunnerService: ScriptRunnerService

    @BeforeEach
    fun setup() {
        scriptRunnerService = ScriptRunnerService()
    }

    @Test
    fun `a process is run and executes a python script`() {
        val result = scriptRunnerService.runPyScript("test.py")
        assertTrue(result is Result.Success<*>)
    }

}