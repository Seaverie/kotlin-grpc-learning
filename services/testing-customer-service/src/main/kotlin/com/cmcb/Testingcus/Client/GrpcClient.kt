package com.cmcb.testingcus.client

import com.cmcb.platform.observability.AdapterConfig
import com.cmcb.testing.customer.proto.CustomerServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.netty.GrpcSslContexts
import io.grpc.netty.NettyChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class GrpcClient {

    @Bean(destroyMethod = "shutdown")
    fun customerChannel(adapters: AdapterConfig): ManagedChannel {
        val cfg = adapters.channels["customer"]
            ?: error("missing customer channel configuration mapping")

        val (host, port) = cfg.address.split(":").let {
            it[0] to it[1].toInt()
        }

        val builder = NettyChannelBuilder.forAddress(host, port).usePlaintext()
        return builder.build();
    }

    @Bean
    fun customerStub(customerChannel: ManagedChannel): CustomerServiceGrpc.CustomerServiceBlockingStub {
        return CustomerServiceGrpc.newBlockingStub(customerChannel)
    }
}