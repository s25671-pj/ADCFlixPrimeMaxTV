package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses.BadRequestException;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.ExceptionHandler.RTEClasses.NotFoundException;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController.Service.MovieService;

import java.util.List;

@Service
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        if (movieService.getMovieById(id) != null) {
            return ResponseEntity.ok(movieService.getMovieById(id));
        } else {
            throw new NotFoundException("Not found -- 404");
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Movie> addNewMovie(@RequestBody Movie addedMovie) {

        if (addedMovie.getName() != null
                && addedMovie.getCategory() != null
                && addedMovie.getPrice() != null) {

            addedMovie = movieService.addMovie(addedMovie);
            return ResponseEntity.ok(addedMovie);
        } else {
            throw new BadRequestException("Bad request -- 400");
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Movie> updateSomeMovie(@RequestBody Movie updatedMovie, @PathVariable int id) {
        if (movieService.getMovieById(id) != null) {
            if (updatedMovie.getName() != null
                    && updatedMovie.getCategory() != null
                    && updatedMovie.getPrice() != null) {

                updatedMovie.setId(id);
                movieService.updateMovie(id);
                return ResponseEntity.ok(updatedMovie);
            }
        } else {
            throw new NotFoundException("Not found -- 404");
        }
        throw new BadRequestException("Bad request -- 400");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSomeMovie(@PathVariable int id) {
        if (movieService.getMovieById(id) != null) {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new NotFoundException("Not found -- 404");
        }
    }
}