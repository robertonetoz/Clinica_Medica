package br.edu.imepac.repositories;


import br.edu.imepac.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {

}
