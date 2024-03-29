package backend.course.spring.unik.repository;

import backend.course.spring.unik.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post ORDER BY post LIMIT 4", nativeQuery = true)
    List<Post> findFourPosts();
}
