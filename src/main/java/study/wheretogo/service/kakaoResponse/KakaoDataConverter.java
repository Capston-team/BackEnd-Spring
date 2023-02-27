package study.wheretogo.service.kakaoResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.wheretogo.data.responseData.ResponseData;
import study.wheretogo.service.readJson.ReadCarrierStores;
import study.wheretogo.service.readJson.ReadDiscountRate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class KakaoDataConverter {

    private final KakaoSearchApi kakao;
    private final ReadCarrierStores stores;
    private final ReadDiscountRate discountRate;

    private final KakaoDataMapper mapper;


    public List<ResponseData> convert(String categoryGroupCode, String carrier, String grade, String longitude, String latitude) {

        try {
            JsonArray array = stores.read(carrier, categoryGroupCode);

            List<ResponseData> responseList = new ArrayList<>();

            for (JsonElement element : array) {

                JsonObject object = element.getAsJsonObject();
                String code = object.get("code").getAsString();
                JsonArray asJsonArray = object.get("stores").getAsJsonArray();
                responseList.addAll(convertStores(carrier, grade, longitude, latitude, code, asJsonArray));
            }

            return responseList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ResponseData> convertStores(String carrier, String grade, String longitude, String latitude, String code, JsonArray stores) throws IOException {
        List<ResponseData> responseList = new ArrayList<>();
        // 매장 목록
        for (JsonElement store : stores) {
            Map<String, Object> response = kakao.search(code, store.getAsString(), longitude, latitude);
            int rate = discountRate.read(carrier, grade, store.getAsString());
            Object StoreList = response.getOrDefault("documents", new ArrayList<ResponseData>());
            mapper.map(store.getAsString(), StoreList, rate).ifPresent(responseList::add);
        }
        return responseList;
    }
}
