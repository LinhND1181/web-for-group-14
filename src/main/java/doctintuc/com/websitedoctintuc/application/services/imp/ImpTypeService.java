package doctintuc.com.websitedoctintuc.application.services.imp;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import doctintuc.com.websitedoctintuc.application.constants.DevMessConstant;
import doctintuc.com.websitedoctintuc.application.constants.UserMessConstant;
import doctintuc.com.websitedoctintuc.application.mapper.TypeMapper;
import doctintuc.com.websitedoctintuc.application.repository.TypeRepository;
import doctintuc.com.websitedoctintuc.application.services.ITypeService;
import doctintuc.com.websitedoctintuc.config.exception.VsException;
import doctintuc.com.websitedoctintuc.domain.dto.TypeDTO;
import doctintuc.com.websitedoctintuc.domain.entity.Type;

@Service
public class ImpTypeService implements ITypeService {

	private final TypeRepository typeRepository;
	private final TypeMapper typeMapper = Mappers.getMapper(TypeMapper.class);

	public ImpTypeService(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<Type> listType(Integer page, Integer size) {
		List<Type> listCategories;
		if (page != null) {
			listCategories = typeRepository.findAll(PageRequest.of(page.intValue(), size)).getContent();
		} else {
			listCategories = typeRepository.findAll();
		}
		return listCategories;
	}

	@Override
	public Type createNewType(TypeDTO typeDTO) {
		Type typeName = typeRepository.findByTypeName(typeDTO.getNameType());
		if (typeName != null) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.DUPLICATE_NAME, typeName.getTypeName()));
		} else {
			Type newType = typeMapper.toType(typeDTO);
			return typeRepository.save(newType);
		}
	}

	@Override
	public Type updateTypeById(TypeDTO typeDTO, Integer id) {
		Optional<Type> foundType = typeRepository.findById(id);
		if (foundType.get().getActiveFlag() == true) {
			if (foundType.isEmpty()) {
				throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
						String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "type", id));
			} else {
				Type type = typeRepository.findByTypeName(typeDTO.getNameType());
				if (type != null) {
					throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
							String.format(DevMessConstant.Common.EXITS_NAME, typeDTO.getNameType()));
				} else {
					foundType = Optional.ofNullable(typeMapper.toType(typeDTO));
					foundType.get().setId(id);
					typeRepository.save(foundType.get());
					return foundType.get();
				}
			}
		} else {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, typeDTO.getNameType()));
		}
	}

	@Override
	public Type getTypeById(Integer id) {
		Optional<Type> foundType = typeRepository.findById(id);
		if (foundType.isEmpty() || foundType.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "type", id));
		}
		return foundType.get();
	}

	@Override
	public String deleteTypeById(Integer id) {
		Optional<Type> foundById = typeRepository.findById(id);
		if (foundById.isEmpty()) {
			throw new VsException(UserMessConstant.ERR_NO_DATA_RESULT,
					String.format(DevMessConstant.Common.NOT_FOUND_OBJECT_BY_ID, "type", id));
		}
		if (foundById.get().getActiveFlag() == false) {
			throw new VsException(UserMessConstant.ERR_ID_UNACTIVE_RESULT,
					String.format(DevMessConstant.Common.UNACTIVE_ID, foundById.get().getTypeName()));
		} else {
			foundById.get().setActiveFlag(Boolean.FALSE);
			foundById.get().setDeleteFlag(Boolean.TRUE);
			typeRepository.save(foundById.get());
		}
		return "Upset active of type successfully!";
	}

}
