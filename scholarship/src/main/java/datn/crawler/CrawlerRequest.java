package datn.crawler;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CrawlerRequest<R> {
	private String url;
    private HttpMethod httpMethod;
    private Class<R> repsonseType;
    private Map<String, String> params = new HashMap<>();
    private Object body;

    public CrawlerRequest(String url, HttpMethod method, Class<R> type) {
        this.url = url;
        this.httpMethod = method;
        this.repsonseType = type;
    }

    public CrawlerRequest<R> addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public R send() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (this.params.size() > 0) {
            params.entrySet().forEach(entry -> {
                builder.queryParam(entry.getKey(), entry.getValue());
            });
        }
        ResponseEntity<R> response;
        if (this.httpMethod.equals(HttpMethod.POST) || this.httpMethod.equals(HttpMethod.PUT)){
            response = restTemplate.exchange(builder.toUriString(), this.httpMethod, createHeaderWithBody(), this.repsonseType);
        } else {
            response = restTemplate.exchange(builder.toUriString(), this.httpMethod, createHeader(), this.repsonseType);
        }
        return response.getBody();
    }


    private HttpEntity<String> createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return httpEntity;
    }

    private HttpEntity<?> createHeaderWithBody() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(this.body, headers);
    }

}
