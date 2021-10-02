import kong.unirest.GenericType;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import models.Post;
import models.User;
import utils.APIUtils;
import utils.FileUtils;
import utils.JsonUtils;
import utils.SortUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        {
            Unirest.config().defaultBaseUrl("https://jsonplaceholder.typicode.com/");
            Unirest.config().setObjectMapper(new JacksonObjectMapper());
        }
        System.out.println(Unirest.post("posts").header("Content-Type", "application/json").body(new Post(1,"asd","asd")).asJson().getBody().toString());
    }
}
