import { Injectable, Component, OnInit } from '@angular/core';
import {CinemaBackendService} from '../../services/cinemaBackend.service';
import { User } from '../../model/cinema.model';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import {AppComponent} from "../../app.component";

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

    message: string = '';

    ngOnInit() {
    }

    userFromJson(json) {
      return {
          id: json['id'],
          email: json['email'],
          password: json['password'],
          username: json['username'],
          role: json['role'],
          fullName: json['fullName'],
          phoneNumber: json['phoneNumber'],
          age: json['age'],
          address: json['address']
      }
    }
    title = 'login';
      user: User = {
      id: 0,
      email: "",
      password: "",
      username: "",
      role: "",
      fullName: "",
      phoneNumber: "",
      age: 0,
      address: ""
    };

    constructor(
                private cinemaBackendService: CinemaBackendService,
                private router: Router,
                private _cookieService:CookieService) {
    }

    login() {
        if (this.cinemaBackendService.isLoggedIn) {
          this.router.navigate(['/screenings']);
        }

        this.cinemaBackendService.login(this.user).subscribe(response => {
            if (response != null) { //;sikerült bejelentkezni
				this._cookieService.put("user", this.cinemaBackendService.user.username);
                this.cinemaBackendService.isLoggedIn = true;
                this.cinemaBackendService.user = this.userFromJson(response);
                this.router.navigate(['/screenings']);
            }
            else {
                alert("Hibás felhasználónév/email vagy jelszó!");
            }
        }, err => {
        });
    }
    register() {
        this.router.navigate(['/register']);
    }
}
