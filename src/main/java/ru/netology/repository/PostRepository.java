package ru.netology.repository;


import org.springframework.stereotype.Repository;
import ru.netology.exception.PostNotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private static ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(posts.size());

    public List<Post> all() {
        return posts.values().stream()
                .filter(p -> !p.isRemoved())
                .collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id))
                .filter(p -> !p.isRemoved());
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = count.incrementAndGet();
            post.setId(newId);
            posts.put(newId, post);
            return post;
        } else if (getById(post.getId()).isPresent()) {
            return posts.compute(post.getId(), (id, existingPost) -> {
                if (existingPost != null) {
                    existingPost.setContent(post.getContent());
                    existingPost.setPublicSend(post.isPublicSend());
                    return existingPost;
                } else {
                    return null;
                }
            });
        }
        return null;
    }

    public void removeById(long id) {
        Post post = posts.get(id);
        if (post != null) {
            post.setRemoved(true);
        } else {
            throw new PostNotFoundException("Post with id " + id + " not found.");
        }
    }
}
