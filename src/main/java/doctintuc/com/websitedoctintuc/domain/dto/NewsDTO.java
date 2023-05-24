package doctintuc.com.websitedoctintuc.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsDTO {
	private String newsName;

	private String title;

	private String content;

	private String tags;

	private MultipartFile video;

	private MultipartFile podcast;
}
