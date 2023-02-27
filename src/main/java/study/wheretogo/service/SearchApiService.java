package study.wheretogo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.wheretogo.data.responseData.ResponseData;
import study.wheretogo.service.kakaoResponse.KakaoDataConverter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchApiService {


    private final KakaoDataConverter converter;
    public List<ResponseData> getResponseData(String category, String carrier, String grade, String longitude, String latitude) {
        return converter.convert(category, carrier, grade, longitude, latitude);
    }
}
