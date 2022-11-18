package com.projetoapi.projeto_api.Servico;

import com.projetoapi.projeto_api.Model.Mensagem;
import com.projetoapi.projeto_api.Model.Pessoa;
import com.projetoapi.projeto_api.Repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;
    @Autowired
    private Repositorio acao;

    public ResponseEntity<?> cadastrar(Pessoa pessoa) {
        if (pessoa.getNome().equals("")) {
            mensagem.setMensagem("Erro, o nome não pode estar vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (pessoa.getIdade() < 18) {
            mensagem.setMensagem("Erro, a idade precisa ser maior ou igual a 18 anos.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(pessoa), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo (int codigo) {
        if (acao.countByPrimaryKey(codigo) == 0) {
            mensagem.setMensagem("Erro, esta pessoa não foi encontrada");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.findByPrimaryKey(codigo), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editar(Pessoa pessoa) {
        if (acao.countByPrimaryKey(pessoa.getPrimaryKey()) == 0) {
            mensagem.setMensagem("A pessoa informada não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (pessoa.getNome().equals("")) {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (pessoa.getIdade() < 0) {
            mensagem.setMensagem("Informe uma mensagem válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(pessoa), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int primaryKey) {
        if (acao.countByPrimaryKey(primaryKey) == 0) {
            mensagem.setMensagem("Este registro não foi encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            Pessoa pessoa = acao.findByPrimaryKey(primaryKey);
            acao.delete(pessoa);
            mensagem.setMensagem("Pessoa deletada com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
