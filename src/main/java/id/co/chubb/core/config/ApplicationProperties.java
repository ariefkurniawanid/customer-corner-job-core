package id.co.chubb.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    CustomerCornerProperties customerCornerProperties = new CustomerCornerProperties();

    public CustomerCornerProperties getReportingEodProperties() {
        return customerCornerProperties;
    }

    public static class CustomerCornerProperties {

    }
}
