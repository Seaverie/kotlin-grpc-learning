package com.cmcb.cus.service

import com.cmcb.platform.observability.GetContextInterceptor
import com.cmcb.testing.customer.proto.CustomerServiceGrpc
import com.cmcb.testing.customer.proto.req
import com.cmcb.testing.customer.proto.res
import io.grpc.Context
import io.grpc.stub.StreamObserver
import org.springframework.grpc.server.service.GrpcService

@GrpcService(interceptors = [GetContextInterceptor::class])
class CustomerServer : CustomerServiceGrpc.CustomerServiceImplBase() {

    override fun getCustomer(request: req?, responseObserver: StreamObserver<res?>?) {
        println("getting customer server")

        val customerNumber = GetContextInterceptor.CUSTOMER_NO_CONTEXT.get();
        val auth = GetContextInterceptor.AUTHENTICATION_KEY.get();
        println(customerNumber)
        println(auth)

        responseObserver?.onNext(
            res.newBuilder().setName(request?.name).build()
        )
        responseObserver?.onCompleted()
    }

}