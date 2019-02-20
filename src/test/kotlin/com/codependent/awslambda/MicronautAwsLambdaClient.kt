package com.codependent.awslambda

import io.micronaut.function.client.FunctionClient
import io.reactivex.Single
import javax.inject.Named

@FunctionClient
interface MicronautAwsLambdaClient {

    @Named("micronaut-aws-lambda")
    fun index(): Single<String>

}
