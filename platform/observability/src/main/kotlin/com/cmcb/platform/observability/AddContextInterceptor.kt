package com.cmcb.platform.observability

import io.grpc.Metadata

class AddContextInterceptor {
    companion object {
        val CUSTOMER_NO: Metadata.Key<String> =
            Metadata.Key.of("customer_no", Metadata.ASCII_STRING_MARSHALLER)

        val AUTHENTICATION_KEY : Metadata.Key<String> =
            Metadata.Key.of("authentication_key", Metadata.ASCII_STRING_MARSHALLER)
    }
}