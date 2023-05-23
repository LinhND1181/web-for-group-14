package doctintuc.com.websitedoctintuc.application.services;

import java.util.List;

import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;

public interface INewsService {
	
	List<News> findAllNews(Integer page, Integer size);
	
	News createNewNews(NewsDTO newsDTO);
	
}
