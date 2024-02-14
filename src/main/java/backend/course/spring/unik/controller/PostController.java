package backend.course.spring.unik.controller;

import backend.course.spring.unik.dto.request.PostRequest;
import backend.course.spring.unik.dto.response.PostResponse;
import backend.course.spring.unik.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/newPost")
    public String createPost(@RequestBody PostRequest request){
        return postService.post(request);
    }

    @GetMapping("/get")
    public List<PostResponse> getFourPosts() {
        return postService.getFourPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile multipartFile,@PathVariable @RequestParam Long id) {
        return postService.uploadImage(multipartFile,id);
    }
}
