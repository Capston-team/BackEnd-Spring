package study.wheretogo.service.readJson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import study.wheretogo.exception.InvalidParameterException;
import study.wheretogo.exception.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class ReadCarrierStores {

    public JsonArray read(String carrier, String category) throws IOException {

        if(carrier == null || carrier.isEmpty()) {
            throw new InvalidParameterException("carrier");
        }

        if(category == null || category.isEmpty()) {
            throw new InvalidParameterException("category");
        }

        Gson gson = new Gson();
        String path =  "Stores/" + carrier.toUpperCase() + "_Stores.json";

        Resource resource = new ClassPathResource(path);

        if(!resource.exists()) {
            throw new ResourceNotFoundException(path);
        }

        InputStream inputStream = resource.getInputStream();

        String jsonString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        JsonElement json = gson.fromJson(jsonString, JsonElement.class);

        JsonObject obj = json.getAsJsonObject().get(carrier).getAsJsonObject();


        if(!obj.has(category)) {
            throw new ResourceNotFoundException(category);
        }

        return obj.get(category).getAsJsonArray();
    }
}
