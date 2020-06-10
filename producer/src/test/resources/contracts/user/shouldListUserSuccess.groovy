import org.springframework.cloud.contract.spec.Contract
[
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
                parameter 'limit': 100
                parameter 'filter': $(consumer(equalTo("email")))
                parameter 'gender': $(consumer(containing("[mf]")), producer('mf'))
                parameter 'offset': $(consumer(matching("[0-9]+")), producer("1234"))
            }
        }
    }
    response {
        status OK()
        body(
                status: 'success',
                data: [
                        id: $(consumer(123456), producer(anyNumber())),
                        name: $(anyNonBlankString())
                ]
        )
    }
},

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
                parameter 'gender': value(consumer(containing("[mf]")), producer('mf'))
                parameter 'offset': value(consumer(matching("[0-9]+")), producer("1234"))
            }
        }
    }
    response {
        status NOT_FOUND()
        body(
                status: 'success',
                data: [

                ]
        )
    }
}
,
Contract.make {
    name("Create User Success")
    request {
        method POST()
        headers {
            header('key', 'value')
            contentType(applicationJson())
        }
        urlPath("/users")
        body(
                requestId: $(consumer(anyUuid()), producer("FSfSFSF1231312312FDGD")),
                name: $(consumer(anyNonBlankString()))
        )
    }
    response {
        status OK()
        body(
                status: 'success',
                data: [
                    requestId: fromRequest().body('$.requestId'),
                    name: $(producer(nonBlank())),
                    id: $(producer(anyNumber()))
                ]

        )
    }
}
]