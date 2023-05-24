package doctintuc.com.websitedoctintuc.application.services.imp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import doctintuc.com.websitedoctintuc.application.constants.DevMessConstant;
import doctintuc.com.websitedoctintuc.application.constants.UserMessConstant;
import doctintuc.com.websitedoctintuc.application.mapper.CategoryMapper;
import doctintuc.com.websitedoctintuc.application.repository.CategoryRepository;
import doctintuc.com.websitedoctintuc.application.services.ICategoryService;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;
import doctintuc.com.websitedoctintuc.domain.entity.base.AbstractBase;

@Service
public class ImpCategoryService implements ICategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

	public ImpCategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> listCategory(Integer page, Integer size) {
		List<Category> listCategories;
		if (page != null) {
			listCategories = categoryRepository.findAll(PageRequest.of(page.intValue(), size)).getContent();
		} else {
			listCategories = categoryRepository.findAll();
		}
		return listCategories;
	}

	@Override
	public Category createNewCategory(CategoryDTO categoryDTO) {
		Category categoryName = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
		if (categoryName != null) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.DUPLICATE_NAME, categoryName.getCategoryName()));
		} else {
			Category newCategory = categoryMapper.toCategory(categoryDTO);
			return categoryRepository.save(newCategory);
		}
	}

	@Override
	public Category updateCategoryById(CategoryDTO categoryDTO, Integer id) {
		Optional<Category> foundCategory = categoryRepository.findById(id);
		if (foundCategory.get().getActiveFlag() == true) {
			if (foundCategory.isEmpty()) {
				throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
						String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "category", id));
			} else {
				Category category = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
				if (category != null) {
					throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
							String.format(DevMessConstant.Common.EXITS_NAME, categoryDTO.getCategoryName()));
				} else {
					foundCategory = Optional.ofNullable(categoryMapper.toCategory(categoryDTO));
					foundCategory.get().setId(id);
					categoryRepository.save(foundCategory.get());
					return foundCategory.get();
				}
			}
		} else {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, categoryDTO.getCategoryName()));
		}
	}

	@Override
	public Category getCategoryById(Integer id) {
		Optional<Category> foundCategory = categoryRepository.findById(id);
		if (foundCategory.isEmpty() || foundCategory.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "category", id));
		}
		return foundCategory.get();
	}

	@Override
	public String deleteCategoryById(Integer id) {
		Optional<Category> foundById = categoryRepository.findById(id);
		if (foundById.isEmpty()) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "category", id));
		}
		if (foundById.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, foundById.get().getCategoryName()));
		} else {
			foundById.get().setActiveFlag(Boolean.FALSE);
			foundById.get().setDeleteFlag(Boolean.TRUE);
			categoryRepository.save(foundById.get());
		}
		return "Upset active of category successfully!";
	}

}
