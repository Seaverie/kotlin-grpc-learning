package com.cmcb.platform.observability

import io.grpc.Context
import io.grpc.Contexts
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import org.springframework.stereotype.Component

@Component
class GetContextInterceptor : ServerInterceptor {
    companion object {
        val CUSTOMER_NO_CONTEXT : Context.Key<String> = Context.key("customer_no")
        val AUTHENTICATION_KEY : Context.Key<String> = Context.key("authentication_key")
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT?, RespT?>?,
        headers: Metadata?,
        next: ServerCallHandler<ReqT?, RespT?>?
    ): ServerCall.Listener<ReqT?>? {

        val customerNo = headers?.get(AddContextInterceptor.CUSTOMER_NO)
        val authKey = headers?.get(AddContextInterceptor.AUTHENTICATION_KEY)

        val context = Context.current().withValue(CUSTOMER_NO_CONTEXT, customerNo)
            .withValue(AUTHENTICATION_KEY, authKey)

        return Contexts.interceptCall(context, call, headers, next)

    }

}