package contracts.postApiExample

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name("Some spacial name")
    description('''
given:
    An input
when:
    Sth happens
then:
    Output
''')
    request {
        method POST()
        headers {
            header('key', 'value')
            contentType(applicationJson())
        }
        cookies {
            cookie('another_key', 'another_value')
        }
        urlPath("/data") {
            queryParameters {
                parameter 'limit': 100
                parameter 'filter': equalTo("email")
                parameter 'gender': value(consumer(containing("[mf]")), producer('mf'))
                parameter 'offset': value(consumer(matching("[0-9]+")), producer("1234"))
            }
        }
    }
    response {
        status OK()
        //...
    }
}