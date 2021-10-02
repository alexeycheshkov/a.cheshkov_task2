import aquality.selenium.core.logging.Logger;
import kong.unirest.GenericType;
import models.Post;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.internal.collections.Pair;
import utils.JsonUtils;
import utils.SortUtils;

import java.util.List;

public class RestAPITest {
    private final String RANDOM_TEXT = RandomStringUtils.randomAlphanumeric(25);
    private final String POSTS_JSON_PATH = "src/main/resources/expectedresults/posts.json";
    private final String POST_99_JSON_PATH = "src/main/resources/expectedresults/post99.json";
    private final String USERS_JSON_PATH = "src/main/resources/expectedresults/users.json";
    private final String USER_5_JSON_PATH = "src/main/resources/expectedresults/user5.json";
    private final SoftAssert softAssert = new SoftAssert();

    @Parameters({"user_number","post_number","empty_post_number"})
    @Test
    public void restApiTest(int userNumber, int postNumber, int emptyPostNumber) {
        Logger.getInstance().info("Sending GET request for get all posts");
        Pair<Integer, List<Post>> postsAndStatusPair = ApiAppRequest.getPosts();
        Assert.assertEquals((int) postsAndStatusPair.first(), Status.OK.getCode(),"Response status doesn't equal expected: "+ Status.OK.getCode());
        List<Post> expectedPostList = JsonUtils.deserializationObjects(POSTS_JSON_PATH, new GenericType<>(){});
        List<Post> postsList = postsAndStatusPair.second();
        Assert.assertEquals(postsList,expectedPostList,"Objects list doesn't contain JSON format or empty");
        Assert.assertTrue(SortUtils.isSortedPostsById(postsList),"Objects list isn't sorted by Id");

        Logger.getInstance().info("Sending GET request for get post #"+postNumber);
        Pair<Integer, Post> postAndStatusPair = ApiAppRequest.getPost(postNumber);
        Post post_99 = JsonUtils.deserializationObject(POST_99_JSON_PATH, Post.class);
        Assert.assertEquals((int)postAndStatusPair.first(), Status.OK.getCode(),"Response status doesn't equal expected: "+ Status.OK.getCode());
        softAssert.assertEquals(postAndStatusPair.second().getUserId(),post_99.getUserId(),"User Id doesn't equal expected Id: "+post_99.getUserId()+" in post #"+postNumber);
        softAssert.assertEquals(postAndStatusPair.second().getId(),post_99.getId(),"Post Id doesn't equal expected Id: "+post_99.getId()+" in post #"+postNumber);
        softAssert.assertNotNull(postAndStatusPair.second().getTitle(),"In post #"+postNumber+" title is null");
        softAssert.assertNotNull(postAndStatusPair.second().getBody(), "In post #"+postNumber+" body is null");

        Logger.getInstance().info("Sending GET request for get post #"+emptyPostNumber);
        Pair<Integer, Post> emptyPostAndStatusPair = ApiAppRequest.getPost(emptyPostNumber);
        Assert.assertEquals((int)emptyPostAndStatusPair.first(), Status.NOT_FOUND.getCode(),"Response status doesn't equal expected: "+ Status.NOT_FOUND.getCode());
        Assert.assertNull(emptyPostAndStatusPair.second().getBody(),"In post #"+emptyPostNumber+" body isn't null");

        Logger.getInstance().info("Sending POST request for create an entry");
        Post post = new Post(1, RANDOM_TEXT, RANDOM_TEXT);
        Pair<Integer, Post> sentPostPair = ApiAppRequest.sendPost(post);
        Post sentPost = sentPostPair.second();
        Assert.assertEquals((int)sentPostPair.first(), Status.CREATED.getCode(),"Response status doesn't equal expected: "+ Status.CREATED.getCode());
        softAssert.assertEquals(sentPost.getTitle(),post.getTitle(),"Sent post title and got title from response aren't the same");
        softAssert.assertEquals(sentPost.getBody(),post.getBody(),"Sent post body and got body from response aren't the same");
        softAssert.assertEquals(sentPost.getUserId(),post.getUserId(),"Sent post user Id and got user Id from response aren't the same");
        softAssert.assertTrue(sentPost.getId()>0, "Got user Id from response is null");

        Logger.getInstance().info("Sending GET request for get all users");
        Pair<Integer, List<User>> usersAndStatusPair = ApiAppRequest.getUsers();
        Assert.assertEquals((int)usersAndStatusPair.first(), Status.OK.getCode(),"Response status doesn't equal expected: "+ Status.OK.getCode());
        User user5 = JsonUtils.deserializationObject(USER_5_JSON_PATH,User.class);
        List<User> expectedUserList = JsonUtils.deserializationObjects(USERS_JSON_PATH, new GenericType<>(){});
        List<User> usersList = usersAndStatusPair.second();
        Assert.assertEquals(usersList,expectedUserList,"Objects list doesn't contain JSON format or empty");
        Assert.assertEquals(usersList.get(userNumber-1),user5,"Objects list doesn't have expected user or empty");

        Logger.getInstance().info("Sending GET request for get user #"+userNumber);
        Pair<Integer, User> userAndStatusPair = ApiAppRequest.getUser(userNumber);
        Assert.assertEquals((int)userAndStatusPair.first(), Status.OK.getCode(),"Response status doesn't equal expected: "+ Status.OK.getCode());
        Assert.assertEquals(userAndStatusPair.second(),usersList.get(userNumber-1),"Got user #"+userNumber+" and got user from object list from last response aren't the same");
        softAssert.assertAll();
    }
}
