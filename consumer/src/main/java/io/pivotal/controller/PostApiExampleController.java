package io.pivotal.controller;

import io.pivotal.model.DataResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostApiExampleController {

    @PostMapping("/data")
    public DataResponse createData() {
        DataResponse data = new DataResponse();

        return data;
    }
}
