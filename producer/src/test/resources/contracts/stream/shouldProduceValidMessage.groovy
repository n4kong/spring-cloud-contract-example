import org.springframework.cloud.contract.spec.Contract

Contract.make {
    // Human readable description
    description 'Should produce valid message'
    // Label by means of which the output message can be triggered
    label 'contract-test.person.created.event'
    // input to the contract
    input {
        // the contract will be triggered by a method
        triggeredBy('createPerson()')
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo 'user-presence'
        headers {
            header('contentType': 'application/json')
            header('amqp_receivedRoutingKey': 'some.routing.key')
        }
        // the body of the output message
        body([
                id  : $(consumer(9), producer(regex("[0-9]+"))),
                name: "me"
        ])
    }
}