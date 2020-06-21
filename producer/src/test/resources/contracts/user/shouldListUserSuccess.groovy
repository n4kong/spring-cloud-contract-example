import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name("List User Success")
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
                parameter 'limit': $(anyNumber(), producer("1234"))
                parameter 'filter': $(consumer(equalTo("email")))
                parameter 'gender': $(consumer(regex("[m|f]")), producer('m'))
                parameter 'offset': $(consumer(regex("[0-9]+")), producer("5678"))
            }
        }
    }
    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body(
                status: 'success',
                data: [[
                               id: $(anyNumber()),
                               name: $(anyNonBlankString())
                       ],
                       [
                               id: $(anyNumber()),
                               name: $(anyNonBlankString())
                       ]
                ]
        )
    }
}