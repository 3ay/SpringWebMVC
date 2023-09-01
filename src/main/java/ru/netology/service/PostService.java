package ru.netology.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.exception.PostNotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    @Autowired
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) throws PostNotFoundException {
        return repository.getById(id).orElseThrow(
                () -> new PostNotFoundException("Post with id " + id + " not found"));
    }

    public Post save(Post post) {
        Post result = repository.save(post);
        if (result == null) {
            throw new PostNotFoundException("Post не найден по ID: " + post.getId());
        }
        return result;
    }

    public void removeById(Long id) {
        repository.removeById(id);
    }
}
