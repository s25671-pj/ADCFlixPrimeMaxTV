package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;
import pl.pjatk.ADCFlixPrimeMaxTV.Repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        movie.setName(updatedMovie.getName());
        movie.setCategory(updatedMovie.getCategory());
        movie.setPrice(updatedMovie.getPrice());
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void makeAvailable(Long id, Boolean available) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id: " + id +
                        " not found."));
        movie.setAvailable(available);
        movieRepository.makeAvailable(available, id);
    }

}