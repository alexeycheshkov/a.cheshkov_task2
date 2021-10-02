package models;

import java.util.Objects;

public class Post implements Comparable<Post>{
    private long userId;
    private long id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }


    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id
                && userId == post.userId
                && title.equals(post.title)
                && body.equals(post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }

    @Override
    public int compareTo(Post post) {
        if (post.id > this.id) {
            return -1;
        } else if (post.id < this.id){
            return 1;
        }
        return 0;
    }
}
