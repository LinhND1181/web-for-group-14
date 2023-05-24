package doctintuc.com.websitedoctintuc.application.services;

import java.util.List;

import doctintuc.com.websitedoctintuc.domain.dto.TypeDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Type;

public interface ITypeService {
	
	List<Type> listType(Integer page, Integer size);

	Type createNewType(TypeDTO TypeDTO);

	Type updateTypeById(TypeDTO TypeDTO, Integer id);

	Type getTypeById(Integer id);

	String deleteTypeById(Integer id);

}
