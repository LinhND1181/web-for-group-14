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
import doctintuc.com.websitedoctintuc.application.services.ITypeService;
import doctintuc.com.websitedoctintuc.domain.dto.TypeDTO;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestApiV1
public class TypeController {

	@Autowired
	private ITypeService iTypeService;

	@GetMapping(UrlConstant.Type.DATA_TYPE)
	@ApiOperation(value = "Get all type")
	public ResponseEntity<?> getFindAllType(@RequestParam(name = "page", required = false) Integer page) {
		return VsResponseUtil.ok(iTypeService.listType(page, CommonConstant.SIZE_OFF_PAGE));
	}

	@PostMapping(UrlConstant.Type.CREATE_TYPE)
	@ApiOperation(value = "Create new type")
	public ResponseEntity<?> createNewType(@RequestBody @Valid TypeDTO typeDTO) {
		return VsResponseUtil.ok(iTypeService.createNewType(typeDTO));
	}

	@PutMapping(UrlConstant.Type.UPDATE_TYPE)
	@ApiOperation(value = "Update old type by id")
	public ResponseEntity<?> updateOldType(@PathVariable("id") Integer id, @RequestBody TypeDTO typeDTO) {
		return VsResponseUtil.ok(iTypeService.updateTypeById(typeDTO, id));
	}

	@GetMapping(UrlConstant.Type.GET_TYPE)
	@ApiOperation(value = "Get type by id")
	public ResponseEntity<?> getTypeById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iTypeService.getTypeById(id));
	}

	@DeleteMapping(UrlConstant.Type.DELETE_TYPE)
	@ApiOperation(value = "Upset active type by id")
	public ResponseEntity<?> upsetActiveTypeById(@PathVariable("id") Integer id) {
		return VsResponseUtil.ok(iTypeService.deleteTypeById(id));
	}

}
