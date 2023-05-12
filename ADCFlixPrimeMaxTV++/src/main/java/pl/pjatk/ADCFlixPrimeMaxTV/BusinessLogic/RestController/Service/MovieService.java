package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController.Service;

import org.springframework.stereotype.Service;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Category;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses.NotFoundException;

import java.util.List;

@Service
public class MovieService {

    public List<Movie> getAllMovies() {
        return List.of(new Movie(1, "Sin City", 25.0, Category.NOIR),
                new Movie(2, "Niebezpieczni Dzentelmeni", 30.0, Category.POLISH),
                new Movie(3, "The Nun", 28.5, Category.HORROR));
    }

    public Movie getMovieById(int id) {
        List<Movie> allMovies = getAllMovies();
        Movie movieById = null;
        for (Movie movie : allMovies) {
            if (movie.getId() == id) {
                movieById = movie;
                break;
            }
        }
        return movieById;
    }

    public Movie addMovie(Movie movie) {
        return new Movie(movie.getId(), movie.getName(),
                movie.getPrice(), movie.getCategory());
    }

    public Movie updateMovie(int id) {

        Movie movieById = getMovieById(id);
        if (movieById != null) {
            movieById.setName(movieById.getName());
            movieById.setCategory(movieById.getCategory());
            movieById.setPrice(movieById.getPrice());
            return movieById;
        } else {
            throw new NotFoundException("Not found -- 404");
        }
    }

    public void deleteMovie(int id){
        Movie movieById = getMovieById(id);
        if(movieById != null){
            movieById.setName(null);
            movieById.setCategory(null);
            movieById.setPrice(null);

        }else {
            throw new NotFoundException("Not found -- 404");
        }
    }
}