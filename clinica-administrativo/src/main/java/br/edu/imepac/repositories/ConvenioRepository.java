package br.edu.imepac.repositories;


import br.edu.imepac.models.ConvenioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<ConvenioModel, Long> {

}

