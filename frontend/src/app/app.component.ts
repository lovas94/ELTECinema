import {Injectable, Component, OnInit} from '@angular/core';
import {CinemaBackendService} from "./services/cinemaBackend.service";
import {Router} from "@angular/router";
import {CookieService} from "angular2-cookie/services/cookies.service";
import {NgModule} from "@angular/core/src/metadata/ng_module";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

@Injectable()
export class AppComponent {
  constructor(
    private backendService: CinemaBackendService,
    private router: Router,
	private cookieService: CookieService
  ) { }

  register() {
    this.router.navigate(['/register']);
  }

  login() {
    this.router.navigate(['/login']);
  }

  screenings() {
    this.router.navigate(['/screenings']);
  }

  home() {
    this.router.navigate(['']);
  }

  accountDetails() {
    this.router.navigate(['/userdetails'])
  }

  logout() {
    this.backendService.init();
    this.router.navigate(['/login']);
  }

  bookings() {
    this.router.navigate(['/bookings']);
  }

}
