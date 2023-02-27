package study.wheretogo.service.readJson;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

class ReadCarrierStoresTest {

    @Autowired
    private final ReadCarrierStores carrierStores;

    ReadCarrierStoresTest(ReadCarrierStores carrierStores) {
        this.carrierStores = carrierStores;
    }

    @Test
    public void ReadCarrierStoresTest() throws IOException {
        // given
        String carrier = "SKT";
        String categoryGroupCode = "CE7";
        // when
        JsonArray array = carrierStores.read(carrier, categoryGroupCode);
        // then
        System.out.println("array = " + array);

    }



}