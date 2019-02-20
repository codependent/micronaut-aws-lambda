package com.codependent.awslambda

data class LambdaResponse(val isBase64Encoded: Boolean,
                     val statusCode: Int,
                     val headers: Map<String, String>,
                     val body: String)
