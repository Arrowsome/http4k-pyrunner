package me.arrowsome.pyrunner

class ScriptRunnerService {

    fun runPyScript(fileName: String): Result {
        val process = ProcessBuilder(
            "python3",
            System.getProperty("user.home") + "/" + fileName,
        )
            .redirectErrorStream(true)
            .start()

        println("+".repeat(75))
        process
            .inputStream
            .bufferedReader()
            .forEachLine(::println)
        println("+".repeat(75))

        return when (process.waitFor()) {
            0 -> Result.Success(true)
            else -> Result.Failure("Process failed!")
        }
    }
}