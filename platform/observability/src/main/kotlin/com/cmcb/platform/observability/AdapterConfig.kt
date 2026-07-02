package com.cmcb.platform.observability

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("adapters")
data class AdapterConfig(
    val channels: Map<String, ChannelConfig> = emptyMap(),
) {
    data class ChannelConfig(
        val address: String = "",
        val secret: String? = null
    )

}