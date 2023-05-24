package doctintuc.com.websitedoctintuc.application.mapper;

import org.mapstruct.Mapper;

import doctintuc.com.websitedoctintuc.domain.dto.TypeDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Type;

@Mapper(componentModel = "spring")
public interface TypeMapper {
	
	Type toType(TypeDTO typeDto);
	
}
