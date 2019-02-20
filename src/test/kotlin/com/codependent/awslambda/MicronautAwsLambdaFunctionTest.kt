package com.codependent.awslambda

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals


class MicronautAwsLambdaFunctionTest: Spek({

    describe("micronaut-aws-lambda function") {
        val server = ApplicationContext.run(EmbeddedServer::class.java)
        val client = server.applicationContext.getBean(MicronautAwsLambdaClient::class.java)

        it("should return 'micronaut-aws-lambda'") {
            assertEquals(client.index().blockingGet(), "micronaut-aws-lambda")
        }

        afterGroup {
            server.stop()
        }
    }
})
