import { Injectable, Component, OnInit } from '@angular/core';
import {CinemaBackendService} from '../../services/cinemaBackend.service';
import { User } from '../../model/cinema.model';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import {AppComponent} from "../../app.component";

@Component({
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    news: Object;

    ngOnInit() {
    }

    constructor(
                private cinemaBackendService: CinemaBackendService,
                private router: Router,
                private _cookieService:CookieService) {
      this.fgetNews();
    }

    fgetNews() {
      this.cinemaBackendService.getNews().subscribe(
        response => { this.news = response; },
        err => { }
      );
    }

    getNews() {
      return this.news;
    }
}
