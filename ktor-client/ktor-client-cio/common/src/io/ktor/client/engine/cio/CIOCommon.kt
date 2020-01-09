/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.engine.cio

import io.ktor.client.engine.*
import io.ktor.util.*

private val initHook = CIO

/**
 * [HttpClientEngineFactory] using a Coroutine based I/O implementation without additional dependencies
 * with the the associated configuration [CIOEngineConfig].
 *
 * Just supports HTTP/1.x and HTTPS requests.
 */
@KtorExperimentalAPI
object CIO : HttpClientEngineFactory<CIOEngineConfig> {

    init {
        platformInit(this)
    }

    override fun create(block: CIOEngineConfig.() -> Unit): HttpClientEngine =
        CIOEngine(CIOEngineConfig().apply(block))

    override fun toString(): String = "CIO-${Platform().name}"
}

internal expect fun platformInit(factory: HttpClientEngineFactory<CIOEngineConfig>)
