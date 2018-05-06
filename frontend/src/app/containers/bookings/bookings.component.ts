import { Injectable, Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import {CinemaBackendService} from '../../services/cinemaBackend.service';
import {User, Reservation} from '../../model/cinema.model';
import { Router } from '@angular/router';
import { CookieService } from 'angular2-cookie/core';
import {AppComponent} from "../../app.component";

@Component({
    selector: 'bookings',
    templateUrl: './bookings.component.html',
    styleUrls: ['./bookings.component.css']
})
export class BookingComponent implements OnInit {

    reservations = [];
    counter = 0;

    ngOnInit() {
    }

    constructor(
                private cinemaBackendService: CinemaBackendService,
                private router: Router,
                private _cookieService:CookieService,
                private ref: ChangeDetectorRef) {
      if (!this.cinemaBackendService.isLoggedIn) {
        alert("You are not logged in so you can't see this page!");
        this.router.navigate(['/login']);
      }
    }

    getMyBookings() {
        this.cinemaBackendService.getAllReservationToUser(this.cinemaBackendService.user)
          .subscribe( resp => {
            this.reservations.push(resp);
          });
    }

    deleteBooking(id) {
        this.cinemaBackendService.deleteReservation(id).subscribe(resp => {

        }, err => {
          alert("Internal server error.");
        });
        location.reload();
    }

    buy() {
      alert("A proper website would redirect you to a page where you could pay for you tickets. :)");
    }
}
