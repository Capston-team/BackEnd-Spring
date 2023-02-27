package study.wheretogo.data.responseData;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ResponseData {

    private String StoreName;
    private int Discount_rate;
    private List<StoresData> Stores = new ArrayList<>();
}
