package org.example.soapproduct2.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuration class for setting up SOAP web service using Spring-WS.
 * Enables Spring-WS features and defines necessary beans for handling SOAP requests.
 */
@EnableWs
@Configuration
public class WebServiceConfig {

    /**
     * Configures and registers the MessageDispatcherServlet for handling SOAP requests.
     * Sets the application context and enables WSDL location transformation.
     *
     * @param applicationContext The application context.
     * @return ServletRegistrationBean for MessageDispatcherServlet.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet>
    messageDispatcherServletServletRegistrationBean(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Defines the DefaultWsdl11Definition bean for exposing the SOAP web service WSDL.
     * Sets port type, location URI, target namespace, and associates it with the XSD schema.
     *
     * @param productsSchema The XSD schema for the web service.
     * @return DefaultWsdl11Definition bean.
     */
    @Bean(name = "products")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema productsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ProductsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.gproductservice.com");
        wsdl11Definition.setSchema(productsSchema);
        return wsdl11Definition;
    }

    /**
     * Defines the XSD schema for the SOAP web service.
     *
     * @return XsdSchema bean.
     */
    @Bean
    public XsdSchema productsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("products.xsd"));
    }
}