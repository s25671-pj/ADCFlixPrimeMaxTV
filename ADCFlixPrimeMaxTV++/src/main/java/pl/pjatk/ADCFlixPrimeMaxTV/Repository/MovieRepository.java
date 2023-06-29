package pl.pjatk.ADCFlixPrimeMaxTV.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Override
    <S extends Movie> S save(S entity);

    @Modifying
    @Transactional
    @Query("update Movie m set m.isAvailable = :isAvailable where m.id = :id")
    void makeAvailable(@Param("isAvailable") boolean isAvailable, @Param("id") Long id);


}
