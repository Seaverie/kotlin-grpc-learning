package com.cmcb.Testingcus.service

import com.cmcb.platform.observability.AddContextInterceptor
import com.cmcb.testing.customer.proto.CustomerServiceGrpc
import com.cmcb.testing.customer.proto.req
import com.cmcb.testing.customer.proto.res
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import io.grpc.stub.StreamObserver
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class CustomerService (
        private val customerStub: CustomerServiceGrpc.CustomerServiceBlockingStub
    ) : CustomerServiceGrpc.CustomerServiceImplBase() {


    override fun getCustomer(request: req?, responseObserver: StreamObserver<res?>?) {

        println("getting customer testing")

        val request2 = req.newBuilder().setName(request?.name).build();

        val metadataRequest = Metadata()
        metadataRequest.put(AddContextInterceptor.CUSTOMER_NO, "012345" ?: "unknown")
        metadataRequest.put(AddContextInterceptor.AUTHENTICATION_KEY, "12300" ?: "unknown");

        val attachStub = customerStub.withInterceptors(
            MetadataUtils.newAttachHeadersInterceptor(metadataRequest)
        )

        val response = attachStub.getCustomer(request2);

        responseObserver?.onNext(
            res.newBuilder().setName(response.name).build()
        )
        responseObserver?.onCompleted()
    }
}