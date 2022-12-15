package com.likebookapp.service;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.model.dto.PostAndUsersDTO;
import com.likebookapp.model.dto.PostInfoDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public PostService(PostRepository postRepository, MoodRepository moodRepository, ModelMapper mapper, UserRepository userRepository, LoggedUser loggedUser) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public List<PostInfoDTO> getUserPosts(Long id) {
        List<Post> allByUserId = this.postRepository.findAllByUserId(id);
        return allByUserId.stream()
                .map(p ->
                        new PostInfoDTO()
                                .setUserLikes(p.getUserLikes().size())
                                .setContent(p.getContent())
                                .setMood(p.getMood().getName().name())
                                .setId(p.getId())
                ).collect(Collectors.toList());
    }

    public List<PostAndUsersDTO> getOtherUsersPosts(Long id) {
        List<Post> allByUserId = this.postRepository.findAll()
                .stream().filter(p-> !Objects.equals(p.getUser().getId(), id)).collect(Collectors.toList());
        return allByUserId.stream()
                .map(p ->
                        new PostAndUsersDTO()
                                .setUserLikes(p.getUserLikes().size())
                                .setContent(p.getContent())
                                .setMood(p.getMood().getName().name())
                                .setCreater(p.getUser().getUsername())
                                .setId(p.getId())
                ).collect(Collectors.toList());
    }

    @Transactional
    public boolean addPost(AddPostDTO addPostDTO) {
        Post post = mapper.map(addPostDTO, Post.class);
        post.setUser(this.userRepository.getById(loggedUser.getId()));
        post.setMood(this.moodRepository.findByName(addPostDTO.getMood()));

        this.postRepository.save(post);
        return true;
    }

    @Transactional
    public void deletePost(Long id) {
        this.postRepository.deleteById(id);
    }

    @Transactional
    public void likePost(long postId) {
        Post post = this.postRepository.findById(postId).orElse(null);
        User user = this.userRepository.findById(loggedUser.getId()).orElse(null);

        if (post!= null && post.getUserLikes().contains(user)) {
            return;
        }

        if (post != null && user != null) {
            post.getUserLikes().add(user);
            this.postRepository.save(post);
        }
    }
}
