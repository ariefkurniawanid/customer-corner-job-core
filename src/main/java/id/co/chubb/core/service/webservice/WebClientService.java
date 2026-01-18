package id.co.chubb.core.service.webservice;

import id.co.chubb.core.model.dto.commons.ErrorResponse;
import id.co.chubb.core.model.dto.fund.response.ApimJwtResponseDto;
import id.co.chubb.core.model.dto.fund.response.FundQueryResponseDto;
import id.co.chubb.core.model.dto.fund.response.PublicApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class WebClientService {

    private final WebClient webClient;

    /* APIM TOKEN */
    @Value("${app.webservice.url.apimToken}")
    private String apimTokenUrl;
    @Value("${app.webservice.parameterName.apimToken.identity}")
    private String nameIdentityParam;
    @Value("${app.webservice.parameterName.apimToken.valueIdentity}")
    private String valueIdentityParam;
    @Value("${app.webservice.headersName.apimToken.appId}")
    private String nameAppId;
    @Value("${app.webservice.headersName.apimToken.valueAppId}")
    private String valueAppId;
    @Value("${app.webservice.headersName.apimToken.appKey}")
    private String nameAppKey;
    @Value("${app.webservice.headersName.apimToken.valueAppKey}")
    private String valueAppKey;
    @Value("${app.webservice.headersName.apimToken.resource}")
    private String nameResource;
    @Value("${app.webservice.headersName.apimToken.valueResource}")
    private String valueResource;
    @Value("${app.webservice.headersName.apimToken.apiVersion}")
    private String nameApiVersion;
    @Value("${app.webservice.headersName.apimToken.valueApiVersion}")
    private String valueApiVersion;

    /* FUND QUERY */
    @Value("${app.webservice.url.fundQuery}")
    private String fundQueryUrl;
    @Value("${app.webservice.parameterName.fundQuery.effectiveDate}")
    private String nameEffectiveDateParam;
    @Value("${app.webservice.headersName.fundQuery.apiVersion}")
    private String nameApiVersionFundQuery;
    @Value("${app.webservice.headersName.fundQuery.valueApiVersion}")
    private String valueApiVersionFundQuery;
    @Value("${app.webservice.headersName.fundQuery.tenanId}")
    private String nameTenanId;
    @Value("${app.webservice.headersName.fundQuery.valueTenanId}")
    private String valueTenanId;
    @Value("${app.webservice.headersName.fundQuery.userIdentity}")
    private String nameUserIdentity;
    @Value("${app.webservice.headersName.fundQuery.valueUserIdentity}")
    private String valueUserIdentity;
    @Value("${app.webservice.headersName.fundQuery.sourceIndi}")
    private String nameSourceIndi;

    public WebClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ApimJwtResponseDto> getApimToken() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apimTokenUrl)
                        .queryParam(nameIdentityParam, valueIdentityParam)
                        .build()
                )
                .headers(httpHeaders -> {
                    httpHeaders.add(nameAppId, valueAppId);
                    httpHeaders.add(nameAppKey, valueAppKey);
                    httpHeaders.add(nameResource, valueResource);
                    httpHeaders.add(nameApiVersion, valueApiVersion);
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(ApimJwtResponseDto.class);
                    }
                    return response.bodyToMono(ErrorResponse.class)
                            .flatMap(err ->
                                    Mono.error(new RuntimeException(
                                            err.getErrorCode() + " - " + err.getErrorMessage()
                                    ))
                            );
                })
                .timeout(Duration.ofSeconds(10));
    }

    public Mono<FundQueryResponseDto> getFundQuery(String token, String effectiveDate, String sourceIndi) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(fundQueryUrl)
                                .queryParam(nameEffectiveDateParam, effectiveDate)
                                .build()
                )
                .headers(httpHeaders -> {
                    httpHeaders.setBearerAuth(token);
                    httpHeaders.add(nameApiVersionFundQuery, valueApiVersionFundQuery);
                    httpHeaders.add(nameTenanId, valueTenanId);
                    httpHeaders.add(nameUserIdentity, valueUserIdentity);
                    httpHeaders.add(nameSourceIndi, sourceIndi);
                })
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(FundQueryResponseDto.class);
                    }
                    return response.bodyToMono(ErrorResponse.class)
                            .flatMap(err ->
                                    Mono.error(new RuntimeException(
                                            err.getErrorCode() + " - " + err.getErrorMessage()
                                    ))
                            );
                })
                .timeout(Duration.ofSeconds(10));
    }
}
