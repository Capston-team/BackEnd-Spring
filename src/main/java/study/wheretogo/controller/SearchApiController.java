package study.wheretogo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.wheretogo.data.responseData.ResponseData;
import study.wheretogo.service.SearchApiService;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchApiController {

    private final SearchApiService service;

    @GetMapping("/")
    public String healthCheck() {
        return "The service is up and running...";
    }

    @GetMapping("/map")
    public List<ResponseData> GET_mapCoordinate(
            @RequestParam String category,
            @RequestParam String longitude,
            @RequestParam String latitude,
            @RequestParam String carrier,
            @RequestParam String grade) {

        return service.getResponseData(category, carrier, grade, longitude, latitude);
    }

}
