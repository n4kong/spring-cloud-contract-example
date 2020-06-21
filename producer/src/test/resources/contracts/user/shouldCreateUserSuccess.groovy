import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name("Create User Success")
    request {
        method POST()
        headers {
            header('some-key', 'some-value')
            contentType(applicationJson())
        }
        urlPath("/users")
        body(
                requestId: $(anyUuid()),
                name: $(anyNonBlankString(), producer("John"))
        )
    }
    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body(
                status: 'success',
                data: [
                    requestId: fromRequest().body('$.requestId'),
                    name: $(anyNonBlankString()),
                    id: $(anyNumber())
                ]
        )
    }
}