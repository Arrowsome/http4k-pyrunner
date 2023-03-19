package me.arrowsome.pyrunner

import org.kodein.di.*

val di = DI {
    bindSingleton { ApiLenses }
    bindSingleton { ScriptRunnerService() }
    bindSingleton { ApiHandlers(instance(), instance()) }
}