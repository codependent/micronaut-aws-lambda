package com.codependent.awslambda

import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.core.io.IOUtils
import io.micronaut.function.aws.MicronautRequestStreamHandler
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import javax.inject.Inject

class PingLambda : MicronautRequestStreamHandler() {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val log = org.apache.logging.log4j.LogManager.getLogger(PingLambda::javaClass)
    private val objectMapper = ObjectMapper()

    override fun execute(input: InputStream?, output: OutputStream?, context: Context?) {
        val br = BufferedReader(InputStreamReader(input))
        val text = IOUtils.readText(br)

        System.out.println("Probando logs con System.out")
        val lambdaLogger = context?.logger
        lambdaLogger?.log("Probando logs con LambdaLogger")

        log.info("My other log")

        logger.info("Text {}", text)
        logger.info("Context {}", context?.awsRequestId)
        logger.info("Context {}", context?.clientContext?.client)
        logger.info("Context {}", context?.clientContext?.custom)
        logger.info("Context {}", context?.clientContext?.environment)
        logger.info("Context {}", context?.functionName)
        logger.info("Context {}", context?.identity)
        logger.info("Context {}", context?.invokedFunctionArn)
        logger.info("Context {}", context?.logGroupName)
        logger.info("Context {}", context?.logStreamName)
        logger.info("Context {}", context?.memoryLimitInMB)
        logger.info("Context {}", context?.remainingTimeInMillis)

        val response = LambdaResponse(false, 200, emptyMap(),
                objectMapper.writeValueAsString(mapOf("key1" to "value1")))
        logger.info("Response object {}", response)

        val json = objectMapper.writeValueAsString(response)
        logger.info("Response json {}", json)

        output?.write(json.toByteArray())
    }

}
