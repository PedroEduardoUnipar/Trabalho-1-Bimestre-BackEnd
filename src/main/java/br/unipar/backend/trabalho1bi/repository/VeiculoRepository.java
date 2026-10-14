package br.unipar.backend.trabalho1bi.repository;

import br.unipar.backend.trabalho1bi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    List<Veiculo> findByMarca(String marca);

    List<Veiculo> findByAno(Integer ano);

    @Query("SELECT v FROM Veiculo v WHERE "
            + "(:modelo IS NULL OR LOWER(v.modelo) = LOWER(:modelo)) "
            + "AND (:marca IS NULL OR LOWER(v.marca) = LOWER(:marca)) "
            + "AND (:ano IS NULL OR v.ano = :ano)")
    List<Veiculo> pesquisa(@Param("modelo") String modelo,
                          @Param("marca") String marca,
                          @Param("ano") Integer ano);
}
