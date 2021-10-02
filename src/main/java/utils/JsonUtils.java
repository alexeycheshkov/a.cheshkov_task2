package utils;

import aquality.selenium.core.logging.Logger;
import kong.unirest.GenericType;
import kong.unirest.jackson.JacksonObjectMapper;

import java.util.List;

public class JsonUtils {
    public static <T> T deserializationObject(String path, Class<T> postClass){
        Logger.getInstance().debug("Deserializing json object from file");
        return new JacksonObjectMapper().readValue(FileUtils.parseJson(path), postClass);
    }

    public static <T> List<T> deserializationObjects(String path, GenericType<List<T>> genericType){
        Logger.getInstance().debug("Deserializing json objects list from file");
        return new JacksonObjectMapper().readValue(FileUtils.parseJson(path),genericType) ;
    }
}
