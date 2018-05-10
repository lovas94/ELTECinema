import { Injectable, Component, OnInit } from '@angular/core';
import {CinemaBackendService } from '../../services/cinemaBackend.service';
import { Movie } from '../../model/cinema.model';
import { Router } from '@angular/router';

@Component({
    selector: 'screenings',
    templateUrl: './screenings.component.html',
    styleUrls: ['./screenings.component.css'],
})


export class ScreeningsComponent implements OnInit {
    title = 'screenings';
    isAdmin = this.cinemaBackendService.isAdmin();
    movies: Movie[];
    filteredMovies: Movie[];
    titleFilter: string;
    newMovie: Movie = <any>{};

    constructor(private cinemaBackendService: CinemaBackendService, private router: Router) {
      if (!this.cinemaBackendService.isLoggedIn) {
        alert("You are not logged in so you can't see the screenings!");
        this.router.navigate(['/login']);
      }
    }

    ngOnInit() {
        this.load();
    }

    load() {
        this.cinemaBackendService.getMovies().subscribe(movies => {
            this.movies = this.filteredMovies = movies;
            this.newMovie = {
                ageLimit: "",
                director: "",
                dubbed: false,
                story: "",
                ticketSold: 0,
                title: "",
                length: 0
            };
        });
    }

    filterChanged() {
        this.filteredMovies = this.movies.filter(movie => movie.title.toLocaleLowerCase().indexOf(this.titleFilter.toLocaleLowerCase()) >= 0);
    }

    addMovie() {
        this.cinemaBackendService.addMovie(this.newMovie).subscribe(() => {
            this.load();
        });
    }
}
