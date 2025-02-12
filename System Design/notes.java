# System Design
Desigining a system based on certain requirements
-> decide on architecture components, modules

Each component:
-> database and caching
-> scaling & fault tolerance and availability
-> async processing (delegation) -> (eg: let's say you scroll facebook, so system should already keep loaded extra posts when user reaches at end, the extra posts loads and show to not give delayed response)
-> communication(http, https, grpc, tpc)

each component in isolation is:
Fault tolerance -> plan for recovery(eg: data) in case of failure ( to stable state)
Availability -> system even works when component fails
scalable -> horizontally scalable
