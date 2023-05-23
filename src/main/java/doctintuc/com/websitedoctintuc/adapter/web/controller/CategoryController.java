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
import doctintuc.com.websitedoctintuc.application.services.ICategoryService;
import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestApiV1
public class CategoryController {

	@Autowired
	private ICategoryService iCategoryService;

	@GetMapping(UrlConstant.Category.DATA_CATEGORY)
	@ApiOperation(value = "Get all category")
	public ResponseEntity<?> getFindAllBrand(@RequestParam(name = "page", required = false) Integer page) {
		return VsResponseUtil.ok(iCategoryService.listCategory(page, CommonConstant.SIZE_OFF_PAGE));
	}

	@PostMapping(UrlConstant.Category.CREATE_CATEGORY)
	@ApiOperation(value = "Create new category")
	public ResponseEntity<?> createNewCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
		return VsResponseUtil.ok(iCategoryService.createNewCategory(categoryDTO));
	}

	@PutMapping(UrlConstant.Category.UPDATE_CATEGORY)
	@ApiOperation(value = "Update old category by id")
	public ResponseEntity<?> updateOldCategory(@PathVariable("id") Integer id, @RequestBody CategoryDTO categoryDTO) {
		return VsResponseUtil.ok(iCategoryService.updateCategoryById(categoryDTO, id));
	}

	@GetMapping(UrlConstant.Category.GET_CATEGORY)
	@ApiOperation(value = "Get category by id")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iCategoryService.getCategoryById(id));
	}

	@DeleteMapping(UrlConstant.Category.DELETE_CATEGORY)
	@ApiOperation(value = "Upset active category by id")
	public ResponseEntity<?> upsetActiveCategoryById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iCategoryService.deleteCategoryById(id));
	}

}
