package pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.ADCFlixPrimeMaxTV.Blueprints.Movie;
import pl.pjatk.ADCFlixPrimeMaxTV.BusinessLogic.RestController.Service.MovieService;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get list of all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of movies from database",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Operation(summary = "Get specific movie by ID key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found movie with provided ID",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            return ResponseEntity.ok(movieService.getMovieById(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            throw new EntityNotFoundException();
        }
    }

    @Operation(summary = "Add new movie to DB with provided values")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New movie correctly added",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PostMapping
    @ResponseBody
    public ResponseEntity<Movie> addNewMovie(@RequestBody Movie addedMovie) {

        if (addedMovie.getName() != null
                && addedMovie.getCategory() != null
                && addedMovie.getPrice() != null) {

            addedMovie = movieService.addMovie(addedMovie);
            return ResponseEntity.ok(addedMovie);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update movie attributes by chosen ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Movie> updateSomeMovie(@RequestBody Movie updatedMovie, @PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            if (updatedMovie.getName() != null
                    && updatedMovie.getCategory() != null
                    && updatedMovie.getPrice() != null) {

                updatedMovie.setId(id);
                movieService.updateMovie(id, updatedMovie);
                return ResponseEntity.ok(updatedMovie);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete movie by chosen ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie correctly deleted from DB",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSomeMovie(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Set movie availability to 'true' ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie available",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("ava/{id}")
    public ResponseEntity<Movie> setAvailable(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            movieService.makeAvailable(id, true);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Set movie availability to 'false' ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie unavailable",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @PutMapping("nava/{id}")
    public ResponseEntity<Movie> setNotAvailable(@PathVariable Long id) {
        if (movieService.getMovieById(id) != null) {
            movieService.makeAvailable(id, false);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}