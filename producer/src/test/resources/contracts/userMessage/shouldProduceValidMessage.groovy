import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'Should produce valid message'
    label 'contract.user.message'
    input {
        triggeredBy('mockSendUserMessage()')
    }

    outputMessage {
        sentTo 'user.exchange'
        headers {
            header('contentType': 'application/json')
            header('amqp_receivedRoutingKey': 'some.routing.key')
        }
        body([
                id  : $(anyNumber()),
                name: $(anyNonBlankString())
        ])
    }
}