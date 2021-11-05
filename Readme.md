# Async Test

This is example code for StackOverflow question [Link](https://stackoverflow.com/questions/69855138/async-rabbitmq-communcation-using-spring-integration?noredirect=1#comment123481017_69855138)
For this to work, RabbitMQ should be up.

Service1 will send message to RabbitMQ on exchange "" and routing key "test-queue" on ApplicationReadyEvent.
It waits for ListenableFuture so it can process later event.

Service2 listens on "test-queue". It will return ListenableFuture and on sepate thread id will wait 5 seconds to send reply.

Current problem is that Service1 will receive ListenableFuture from RabbitMQ as response and "SuccessfulSessionCreationResponseDTO" which is sent later, never comes to Service1.

In Service1, in class "MessageConverterConfig" trusted package can be commented out, which will cause exception that ListenableFuture can not be deseriazabled.
I understand that event sent after 5 seconds can not be received, because Service1 received ListenableFuture from RabbitMQ as response, instead of "SuccessfulSessionCreationResponseDTO".

To me it looks like, Service2 should be changed, so that result of ListenableFuture is sent as reply instead of ListenableFuture itself.