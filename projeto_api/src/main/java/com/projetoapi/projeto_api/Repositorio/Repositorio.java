package com.projetoapi.projeto_api.Repositorio;

import com.projetoapi.projeto_api.Model.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {

    List<Pessoa> findAll();

    Pessoa findByPrimaryKey(int primaryKey);


    Pessoa findByNome(String nome);

    List<Pessoa> findByOrderByNome();

    @Query(value = "SELECT SUM(idade) FROM pessoa", nativeQuery = true)
    int somaIdades();

    //O ":" é utilizado para extrair o parametro do metodo e para isso é necessario ter o mesmo nome também
    @Query(value = "SELECT * FROM pessoa WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);

    int countByPrimaryKey(int codigo);
}
