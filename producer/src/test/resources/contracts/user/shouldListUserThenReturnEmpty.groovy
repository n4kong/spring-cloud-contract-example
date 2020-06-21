import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name("List User Not Found")
    request {
        method GET()
        headers {
            header('key', 'value')
            contentType(applicationJson())
        }
        cookies {
            cookie('another_key', 'another_value')
        }
        urlPath("/users") {
            queryParameters {
                parameter 'limit': 100
                parameter 'filter': $(consumer(optional(regex("[email]"))), producer(''))
                parameter 'gender': $(consumer(containing("[mf]")), producer('mf'))
                parameter 'offset': $(consumer(matching("[0-9]+")), producer("1234"))
            }
        }
    }
    response {
        status NOT_FOUND()
        body(
                status: 'success',
                data: []
        )
    }
}