package com.codependent.awslambda

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("com.codependent.awslambda")
                .mainClass(Application.javaClass)
                .start()
    }
}
