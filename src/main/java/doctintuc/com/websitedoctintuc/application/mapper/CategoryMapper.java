package doctintuc.com.websitedoctintuc.application.mapper;

import org.mapstruct.Mapper;

import doctintuc.com.websitedoctintuc.domain.dto.CategoryDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	Category toCategory(CategoryDTO categoryDTO);

}
