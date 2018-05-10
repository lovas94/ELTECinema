import { Injectable, Component, OnInit } from '@angular/core';
import {CinemaBackendService} from '../../services/cinemaBackend.service';
import { User } from '../../model/cinema.model';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import {AppComponent} from "../../app.component";

@Component({
    selector: 'userdetails',
    templateUrl: './userdetails.component.html',
    styleUrls: ['./userdetails.component.css']
})
export class UserDetailsComponent implements OnInit {

    isNamePressed: boolean = false;
    isPhoneNumberPressed: boolean = false;
    isAddressPressed: boolean = false;
    isAgePressed: boolean = false;
    isEmailPressed: boolean = false;

    ngOnInit() {
    }

    constructor(
                private cinemaBackendService: CinemaBackendService,
                private router: Router,
                private _cookieService:CookieService) {
      if (!this.cinemaBackendService.isLoggedIn) {
        alert("You are not logged in so you can't see this page!");
        this.router.navigate(['/login']);
      }
    }


    editName( ) {
      this.isNamePressed = true;
    }
    editAddress() {
      this.isAddressPressed = true;
    }

    editPhoneNumber() {
      this.isPhoneNumberPressed = true;
    }

    editAge() {
      this.isAgePressed = true;
    }

    editEmail() {
      this.isEmailPressed = true;
    }
    getUserName() {
      return this.cinemaBackendService.user.username;
    }

    getFullName() {
      return this.cinemaBackendService.user.fullName;
    }

    getAddress() {
      return this.cinemaBackendService.user.address;
    }

    getPhoneNumber() {
      return this.cinemaBackendService.user.phoneNumber;
    }

    getAge() {
      return this.cinemaBackendService.user.age;
    }

    getEmail() {
      return this.cinemaBackendService.user.email;
    }
    update() {
      this.cinemaBackendService.updateUser(this.cinemaBackendService.user).subscribe(resp => {

      },
      err => {

      });

        this.isNamePressed = false;
        this.isPhoneNumberPressed = false;
        this.isAddressPressed = false;
        this.isAgePressed = false;
        this.isEmailPressed= false;
    }
}
