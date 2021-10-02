import kong.unirest.GenericType;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import models.JsonResponse;
import models.Post;
import models.User;
import org.testng.internal.collections.Pair;
import utils.APIUtils;
import java.util.List;

public class ApiAppRequest {
    private static final String BASE_TEST_URL = "https://jsonplaceholder.typicode.com/";
    private static final String POSTS_PREFIX = "posts/";
    private static final String USERS_PREFIX = "users/";

    static {
        Unirest.config().defaultBaseUrl(BASE_TEST_URL);
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
    }

    public static Pair<Integer, List<Post>> getPosts(){
        JsonResponse jsonResponse = APIUtils.get(POSTS_PREFIX);
        return new Pair<>(jsonResponse.getStatusCode(),new JacksonObjectMapper().readValue(jsonResponse.getBody().toString(), new GenericType<>(){}));
    }

    public static Pair<Integer, Post> getPost(int number){
        JsonResponse jsonResponse = APIUtils.get(POSTS_PREFIX+number);
        return new Pair<>(jsonResponse.getStatusCode(),new JacksonObjectMapper().readValue(jsonResponse.getBody().toString(),Post.class));
    }

    public static Pair<Integer, List<User>> getUsers(){
        JsonResponse jsonResponse = APIUtils.get(USERS_PREFIX);
        return new Pair<>(jsonResponse.getStatusCode(),new JacksonObjectMapper().readValue(jsonResponse.getBody().toString(), new GenericType<>(){}));
    }

    public static Pair<Integer, User> getUser(int number){
        JsonResponse jsonResponse = APIUtils.get(USERS_PREFIX+number);
        return new Pair<>(jsonResponse.getStatusCode(),new JacksonObjectMapper().readValue(jsonResponse.getBody().toString(),User.class));
    }

    public static Pair<Integer, Post> sendPost(Post post){
        JsonResponse jsonResponse = APIUtils.post(POSTS_PREFIX, post);
        return new Pair<>(jsonResponse.getStatusCode(), new JacksonObjectMapper().readValue(jsonResponse.getBody().toString(),Post.class));
    }


}
