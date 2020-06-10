package io.pivotal.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.restdocs.operation.OperationRequest;
import org.springframework.restdocs.operation.OperationRequestFactory;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class AddContextPathProcessor implements OperationRequestPreprocessor {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public OperationRequest preprocess(OperationRequest operationRequest) {

        URI newUri = null;
        try {
            URI originalUri = operationRequest.getUri();
            newUri = new URI(originalUri.getScheme(),
                    originalUri.getUserInfo(),
                    originalUri.getHost(),
                    originalUri.getPort(),
                    this.contextPath + originalUri.getPath(),
                    originalUri.getQuery(),
                    originalUri.getFragment());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        OperationRequestFactory factory = new OperationRequestFactory();
        return factory.create(
                    newUri,
                    operationRequest.getMethod(),
                    operationRequest.getContent(),
                    operationRequest.getHeaders(),
                    operationRequest.getParameters(),
                    operationRequest.getParts(),
                    operationRequest.getCookies());
    }
}
