package doctintuc.com.websitedoctintuc.application.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import doctintuc.com.websitedoctintuc.application.repository.NewsRepository;
import doctintuc.com.websitedoctintuc.application.services.INewsService;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;

@Service
public class ImpNewsService implements INewsService {
	
	private final NewsRepository newsRepository = null;
	

	@Override
	public List<News> findAllNews(Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News createNewNews(NewsDTO newsDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
