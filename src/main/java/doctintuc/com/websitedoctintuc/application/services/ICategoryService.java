package doctintuc.com.websitedoctintuc.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;

public interface ICategoryService {

	List<Category> listCategory(Integer page, Integer size);

	Category createNewCategory(CategoryDTO categoryDTO);

	String updateCategoryById(CategoryDTO categoryDTO, Integer id);

	Category getCategoryById(Integer id);

	String deleteCategoryById(Integer id);

}
