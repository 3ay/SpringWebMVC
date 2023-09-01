package ru.netology.model;

public class Post {
    private long id;
    private String content;
    private boolean publicSend;
    private boolean removed;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postInput='" + content + '\'' +
                ", isPublicSend=" + publicSend +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublicSend() {
        return publicSend;
    }

    public void setPublicSend(boolean publicSend) {
        this.publicSend = publicSend;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
