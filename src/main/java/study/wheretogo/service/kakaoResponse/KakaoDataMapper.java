package study.wheretogo.service.kakaoResponse;

import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.wheretogo.data.responseData.ResponseData;
import study.wheretogo.data.responseData.StoresData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KakaoDataMapper {

    public Optional<ResponseData> map(String storeName, Object StoreList, int rate) {


        if(!(StoreList instanceof ArrayList<?>)) {
            return Optional.empty();
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) StoreList;
        if(list.size() == 0) {
            return Optional.empty();
        }

        ResponseData responseData = new ResponseData();
        responseData.setStoreName(storeName);
        responseData.setDiscount_rate(rate);

        List<StoresData> storesDataList = new ArrayList<>();

        for (Map<String, Object> item : list) {
            StoresData store = new StoresData();
            store.setBranch(item.get("place_name").toString());
            store.setLocation(item.get("address_name").toString());
            store.setLatitude(item.get("y").toString());
            store.setLongitude(item.get("x").toString());

            storesDataList.add(store);
        }
        responseData.setStores(storesDataList);

        return Optional.of(responseData);
    }
}
