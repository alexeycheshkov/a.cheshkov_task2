package utils;

import kong.unirest.*;
import models.JsonResponse;

public class APIUtils {

    public static JsonResponse get(String requestPath) {
        HttpResponse<JsonNode> response = Unirest.get(requestPath).asJson();
        Unirest.shutDown();
        return new JsonResponse(response.getStatus(),response.getBody());
    }

    public static <T>JsonResponse post(String requestPath, T post) {
        HttpResponse<JsonNode> response = Unirest.post(requestPath).header("Content-Type", "application/json").body(post).asJson();
        Unirest.shutDown();
        return new JsonResponse(response.getStatus(),response.getBody());
    }
}
