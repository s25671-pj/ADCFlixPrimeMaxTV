package pl.pjatk.ADCFlixPrimeMaxTV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(name = "Movie.findAll")
    List<Movie> getAllById();

    @Query(name = "Movie.find1")
    Optional<Movie> findById(@Param("id") Long id);

    @Query(name = "Movie.delete")
    void deleteMovieById(@Param("id") Long id);

    @Query(name = "Movie.update")
    void updateMovie(@Param("id") Long id, @Param("name") String name, @Param("category") String category, @Param("price") Double price);
}
