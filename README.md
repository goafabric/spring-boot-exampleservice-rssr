# spring-boot-exampleservice-rssr
- an exampleservice that serves the rssr rules:
    - RESTFul
    - Security
    - Sustainibility
    - Resilience
- project build:
    - please build "spring-boot-exampleservice-rssr-parent"
- project start:
    - run ApplicationNRIT
    - Within your browser enter "http://localhost:8080"
    - Example Resource: http://localhost:8080/orderservice1_0/orders/1
    - Credentials: admin / admin
- module description:
    - spring-boot-exampleservice-rssr-parent: project parent
    - spring-boot-exampleservice-rssr-api: the api containing JaxRS annotations
    - spring-boot-exampleservice-rssr-service: the service
    - common-spring-service: common libraries that support the RSSR principles
