package backend.course.spring.unik.service;

import backend.course.spring.unik.dto.request.PostRequest;
import backend.course.spring.unik.dto.response.PostResponse;
import backend.course.spring.unik.entity.Post;
import backend.course.spring.unik.exception.NotFoundException;
import backend.course.spring.unik.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageUploadService uploadService;

    public String post(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .createdDate(LocalDateTime.now())
                .description(request.getDescription())
                .build();

        postRepository.save(post);

        return "Пост успешно создан!";
    }

    public String uploadImage(MultipartFile multipartFile, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found!"));

        post.setImageUrl(uploadService.saveImage(multipartFile));
        postRepository.save(post);

        return "Фотография успешно обновлена!";
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found!"));

        return PostResponse.builder()
                .title(post.getTitle())
                .createdDate(post.getCreatedDate())
                .description(post.getDescription())
                .imageUrl(post.getImageUrl())
                .build();
    }

    public List<PostResponse> getFourPosts() {
        List<Post> posts = postRepository.findFourPosts();

        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post : posts) {
            postResponses.add(PostResponse.builder()
                    .title(post.getTitle())
                    .createdDate(post.getCreatedDate())
                    .imageUrl(post.getImageUrl())
                    .description(post.getDescription())
                    .build());
        }

        return postResponses;
    }
}
