package com.example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.reficio.ws.client.core.SoapClient;
import org.springframework.http.HttpStatus;

/**
 * Created by achalise on 12/1/17.
 */
public class SoapTests {
    @Rule
    public WireMockRule wiremockRule = new WireMockRule(options().port(9990));

    @Test
    public void testSoapRequest() throws Exception {
        String conversionEndpointURI = "/tempconvert.asmx?op=CelsiusToFarenheit";
        String requestXPathQuery = "//soapenv:Envelope | //soapenv:Body | //CelsiusToFahrenheit | //CelsiusToFahrenheit";

        String responseEnvelope = IOUtils.toString(getClass().getResourceAsStream("/soap-requests/response.xml"),"UTF-8");
        String requestEnvelope = IOUtils.toString(getClass().getResourceAsStream("/soap-requests/request.xml"),"UTF-8");

        wiremockRule.stubFor(post(urlEqualTo(conversionEndpointURI)).withHeader("Content-Type", equalTo("application/soap+xml")).withRequestBody(
                matchingXPath(requestXPathQuery)).willReturn(
                aResponse().withStatus(HttpStatus.OK.value()).withBody(responseEnvelope)).withHeader("Content-Type", equalTo("application/soap+xml")));

        SoapClient client = SoapClient.builder().endpointUrl("http://localhost:9990" + conversionEndpointURI).build();
        String response = client.post(requestEnvelope);
        System.out.println(response);
        wiremockRule.verify(postRequestedFor(urlEqualTo(conversionEndpointURI)).withRequestBody(matchingXPath(requestXPathQuery)));


    }
}
