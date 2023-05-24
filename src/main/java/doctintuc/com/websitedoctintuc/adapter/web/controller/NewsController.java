package doctintuc.com.websitedoctintuc.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import doctintuc.com.websitedoctintuc.adapter.web.base.RestApiV1;
import doctintuc.com.websitedoctintuc.adapter.web.base.VsResponseUtil;
import doctintuc.com.websitedoctintuc.application.constants.CommonConstant;
import doctintuc.com.websitedoctintuc.application.constants.UrlConstant;
import doctintuc.com.websitedoctintuc.application.services.INewsService;
import doctintuc.com.websitedoctintuc.domain.dto.NewsDTO;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestApiV1
public class NewsController {

	@Autowired
	private INewsService iNewsService;

	@GetMapping(UrlConstant.News.DATA_NEWS)
	@ApiOperation(value = "Get all news")
	public ResponseEntity<?> getFindAllNews(@RequestParam(name = "page", required = false) Integer page) {
		return VsResponseUtil.ok(iNewsService.findAllNews(page, CommonConstant.SIZE_OFF_PAGE));
	}

	@PostMapping(UrlConstant.News.CREATE_NEWS)
	@ApiOperation(value = "Create new news")
	public ResponseEntity<?> createNewNews(@RequestBody @Valid NewsDTO newsDTO) {
		return VsResponseUtil.ok(iNewsService.createNewNews(newsDTO));
	}

	@PutMapping(UrlConstant.News.UPDATE_NEWS)
	@ApiOperation(value = "Update old news by id")
	public ResponseEntity<?> updateOldNews(@PathVariable("id") Integer id, @RequestBody NewsDTO newsDTO) {
		return VsResponseUtil.ok(iNewsService.updateNewsById(newsDTO, id));
	}

	@GetMapping(UrlConstant.News.GET_NEWS)
	@ApiOperation(value = "Get news by id")
	public ResponseEntity<?> getNewsById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iNewsService.getNewsById(id));
	}

	@DeleteMapping(UrlConstant.News.DELETE_NEWS)
	@ApiOperation(value = "Upset active news by id")
	public ResponseEntity<?> upsetActiveNewsById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iNewsService.deleteNewsById(id));
	}

}
