package doctintuc.com.websitedoctintuc.application.mapper;

import org.mapstruct.Mapper;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;

@Mapper(componentModel = "spring")
public interface NewsMapper {

	News toNews(News news);

}
