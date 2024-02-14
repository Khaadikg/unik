package backend.course.spring.unik.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String title;
    String description;
    String imageUrl;

    @JsonProperty("dd-MM-yyyy")
    LocalDateTime createdDate;
}
