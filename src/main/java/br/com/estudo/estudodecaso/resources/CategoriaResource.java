package br.com.estudo.estudodecaso.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.estudo.estudodecaso.domain.Categoria;
import br.com.estudo.estudodecaso.dto.CategoriaDTO;
import br.com.estudo.estudodecaso.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto) {
		
		Categoria categoria = service.fromDTO(categoriaDto);
		categoria = service.insert(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listCategorias = service.findAll();
		List<CategoriaDTO> listDto = listCategorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDto, @PathVariable Integer id) {
		Categoria categoria = service.fromDTO(categoriaDto);
		categoria.setId(id);
		categoria =  service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction",defaultValue = "ASC") String direction) {
		
		Page<Categoria> listCategorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = listCategorias.map(obj -> new CategoriaDTO(obj));
		
		return ResponseEntity.ok().body(listDto);

	}
	

}
