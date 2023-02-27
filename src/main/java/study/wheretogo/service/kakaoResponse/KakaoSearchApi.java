package study.wheretogo.service.kakaoResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
public class KakaoSearchApi {

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public Map<String, Object> search(String categoryGroupCode, String query, String x, String y) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK "+kakaoApiKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        URI targetUrl = buildSearchUrl(categoryGroupCode, query, x, y);

        ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, Map.class);

        return result.getBody();
    }

    private URI buildSearchUrl(String categoryGroupCode, String query, String x, String y) {
        URI targetUrl = UriComponentsBuilder
                .fromUriString(kakaoApiUrl)
                .queryParam("category_group_code", categoryGroupCode)
                .queryParam("query", query)
                .queryParam("x", x)
                .queryParam("y", y)
                .queryParam("radius", "500")
                .queryParam("size", "15")
                .queryParam("sort", "distance")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
        return targetUrl;
    }
}
