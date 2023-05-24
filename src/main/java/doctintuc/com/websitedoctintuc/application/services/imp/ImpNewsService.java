package doctintuc.com.websitedoctintuc.application.services.imp;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import doctintuc.com.websitedoctintuc.application.constants.DevMessConstant;
import doctintuc.com.websitedoctintuc.application.constants.UserMessConstant;
import doctintuc.com.websitedoctintuc.application.mapper.NewsMapper;
import doctintuc.com.websitedoctintuc.application.repository.NewsRepository;
import doctintuc.com.websitedoctintuc.application.services.INewsService;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import doctintuc.com.websitedoctintuc.domain.entity.News;

@Service
public class ImpNewsService implements INewsService {

	private final NewsRepository newsRepository;
	private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

	public ImpNewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public List<News> findAllNews(Integer page, Integer size) {
		List<News> listCategories;
		if (page != null) {
			listCategories = newsRepository.findAll(PageRequest.of(page.intValue(), size)).getContent();
		} else {
			listCategories = newsRepository.findAll();
		}
		return listCategories;
	}

	@Override
	public News createNewNews(NewsDTO newsDTO) {
		News newsName = newsRepository.findByTitle(newsDTO.getTitle());
		if (newsName != null) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.DUPLICATE_TITLE, newsName.getTitle()));
		} else {
			News newNews = newsMapper.toNews(newsDTO);
			return newsRepository.save(newNews);
		}
	}

	@Override
	public News updateNewsById(NewsDTO newsDTO, Integer id) {
		Optional<News> foundNews = newsRepository.findById(id);
		if (foundNews.get().getActiveFlag() == true) {
			if (foundNews.isEmpty()) {
				throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
						String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "news", id));
			} else {
				News news = newsRepository.findByTitle(newsDTO.getTitle());
				if (news != null) {
					throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
							String.format(DevMessConstant.Common.EXITS_NAME, newsDTO.getTitle()));
				} else {
					foundNews = Optional.ofNullable(newsMapper.toNews(newsDTO));
					foundNews.get().setId(id);
					newsRepository.save(foundNews.get());
					return foundNews.get();
				}
			}
		} else {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, newsDTO.getTitle()));
		}
	}

	@Override
	public News getNewsById(Integer id) {
		Optional<News> foundNews = newsRepository.findById(id);
		if (foundNews.isEmpty() || foundNews.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "news", id));
		}
		return foundNews.get();
	}

	@Override
	public String deleteNewsById(Integer id) {
		Optional<News> foundById = newsRepository.findById(id);
		if (foundById.isEmpty()) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "news", id));
		}
		if (foundById.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, foundById.get().getTitle()));
		} else {
			foundById.get().setActiveFlag(Boolean.FALSE);
			foundById.get().setDeleteFlag(Boolean.TRUE);
			newsRepository.save(foundById.get());
		}
		return "Upset active of news successfully!";
	}

}
