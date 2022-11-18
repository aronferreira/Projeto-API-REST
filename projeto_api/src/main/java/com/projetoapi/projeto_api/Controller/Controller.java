package com.projetoapi.projeto_api.Controller;
import com.projetoapi.projeto_api.Model.Cliente;
import com.projetoapi.projeto_api.Model.Pessoa;
import com.projetoapi.projeto_api.Repositorio.Repositorio;
import com.projetoapi.projeto_api.Repositorio.RepositorioCliente;
import com.projetoapi.projeto_api.Servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Servico servico;
    @Autowired
    private Repositorio acao;

    @Autowired
    private RepositorioCliente acaoCliente;
    //Para especificar o nome da variavel que receberá o valor contido naquele lugar da URL utilizamos {nomeVariavel}
    @PostMapping("/api/cadastrarPessoa")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoaCadastrar) {
        return servico.cadastrar(pessoaCadastrar);
    }

    @GetMapping("/api/listaPessoas")
    public ResponseEntity<?> selecionarPessoas() {
        return servico.selecionar();
    }

    /*@GetMapping("/api/listaPessoas/{primaryKey}")
    public Pessoa selecionarPessoaEspecifica(@PathVariable Integer primaryKey) {
        return acao.findByPrimaryKey(primaryKey);
    }*/


    @GetMapping("/api/listaPessoas/{idPessoa}")
    public ResponseEntity<?> retornarPessoa(@PathVariable int idPessoa) {
        return servico.selecionarPeloCodigo(idPessoa);
    }

    @GetMapping("/{nomeUsuario}")
    //Utilizando PathVariable podemos pegar uma parte da URL como variavel e utilizar para nosso método como retorno.
    public String teste(@PathVariable String nomeUsuario) {
        return "Hello World! " + StringUtils.capitalize(nomeUsuario);
    }

    @GetMapping("/soma/idades")
    public int soma() {
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadesMaior() {
        return acao.idadeMaiorIgual(19);
    }

    @PostMapping("/api/post")
    public Pessoa retornarDadosPessoa(@RequestBody Pessoa pessoa1) {
        return pessoa1;
    }

    @PutMapping("/api/atualizarCadastro")
    public ResponseEntity<?> atualizarCadastro(@RequestBody Pessoa pessoa) {
        return servico.editar(pessoa);
    }

    @DeleteMapping("/api/apagarCadastro/{primaryKey}")
    public ResponseEntity<?> deletarCadastro(@PathVariable Integer primaryKey) {
        return servico.remover(primaryKey);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente cliente) {
        acaoCliente.save(cliente);
    }



}
