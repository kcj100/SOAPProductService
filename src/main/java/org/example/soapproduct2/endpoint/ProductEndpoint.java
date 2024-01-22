package org.example.soapproduct2.endpoint;

import com.gproductservice.GetProductRequest;
import com.gproductservice.GetProductResponse;
import org.example.soapproduct2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * SOAP web service endpoint class for handling getProductRequest.
 * Configured with the @Endpoint annotation to register with Spring WS.
 */
@Endpoint
public class ProductEndpoint {

    /**
     * Namespace URI for the SOAP web service.
     */
    private static final String NAMESPACE_URI = "http://www.gproductservice.com";

    /**
     * Repository for product data.
     */
    private ProductRepository productRepository;

    /**
     * Constructor for ProductEndpoint, injecting the product repository.
     *
     * @param productRepository The repository for product data.
     */
    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Endpoint method for handling getProductRequest.
     * Configured with @PayloadRoot to match the namespace and local part of the request.
     *
     * @param request The getProductRequest received.
     * @return GetProductResponse containing the product information.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest request) {
        GetProductResponse response = new GetProductResponse();
        response.setProduct(productRepository.findProduct(request.getName()));

        return response;
    }
}
