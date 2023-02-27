package study.wheretogo.service.readJson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ReadDiscountRate {

    public int read(String carrier, String grade, String storeName) throws IOException {

        Gson gson = new Gson();
        String path = "Discount/" + carrier.toUpperCase() + "_Discount.json";

        Resource resource = new ClassPathResource(path);

        InputStream inputStream = resource.getInputStream();

        String jsonString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        JsonElement json = gson.fromJson(jsonString, JsonElement.class);
        
        return json.getAsJsonObject()
                .get(carrier).getAsJsonObject()
                .get(storeName).getAsJsonObject()
                .get(grade).getAsInt();

    }
}
