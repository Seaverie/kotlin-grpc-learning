rootProject.name = "gRPC-SubModule-Testing"

include(
    "services:customer-service", "services:testing-customer-service"
    , "proto" , "platform:observability"
)