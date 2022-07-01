package br.com.alura.comex.comex.controllers;

import br.com.alura.comex.comex.controllers.form.AtualizacaoProdutoForm;
import br.com.alura.comex.comex.controllers.form.ProdutoForm;
import br.com.alura.comex.comex.dto.ProdutoDto;
import br.com.alura.comex.comex.models.Produto;
import br.com.alura.comex.comex.repository.CategoriaRepository;
import br.com.alura.comex.comex.repository.ProdutoRepository;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> listar(@RequestParam(required = false) Optional<Integer> pagina) {
        Integer paginaCorreta = 0;
        if (pagina.isPresent()) {
            paginaCorreta = pagina.get();
        }
        Pageable pagination = PageRequest.of(paginaCorreta, 5);

        Page<ProdutoDto> produtos = produtoRepository.findAll(pagination).map(ProdutoDto::new);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(
            @RequestBody @Valid ProdutoForm produtoForm,
            UriComponentsBuilder uriBuilder) {

        Produto produto = produtoForm.converter(categoriaRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder
                .path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
            @RequestBody @Valid AtualizacaoProdutoForm produtoForm) {
        Produto produto = produtoForm.atualizar(id, produtoRepository);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
