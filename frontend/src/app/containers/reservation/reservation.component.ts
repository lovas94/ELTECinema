import { Injectable, Component, OnInit, OnDestroy } from '@angular/core';
import { CinemaBackendService } from '../../services/cinemaBackend.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Screening, Reservation } from '../../model/cinema.model';

@Component({
    selector: 'reservation',
    templateUrl: './reservation.component.html',
    styleUrls: ['./reservation.component.css']
})


export class ReservationComponent implements OnInit, OnDestroy {
    title = 'reservation';
    isAdmin = this.cinemaBackendService.isAdmin();
    movieId: number;
    screenings: Screening[];
    selectedScreening: Screening;
    private sub: any;
    Array = Array;
    reservations: {
        [row: number]: {
            [column: number]: boolean
        }
    };
    reservationList: Reservation[];
    newScreening: Screening = <any>{};
    startTime: string;
    endTime: string;

    missingDetails: boolean = false;

    constructor(private route: ActivatedRoute, private cinemaBackendService: CinemaBackendService, private router: Router) {

      if (!this.cinemaBackendService.isLoggedIn) {
        alert("You are not logged in!");
        router.navigate(['/login']);
      } else {
        if (this.cinemaBackendService.user.address == ""
          || this.cinemaBackendService.user.phoneNumber == ""
          || this.cinemaBackendService.user.fullName == "") {
          this.missingDetails = true;
        }
      }
    }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
            this.movieId = +params.id;
            this.load();
        });
    }

    load() {
        this.cinemaBackendService.getScreeningsByMovie(this.movieId).subscribe(screenings => {
            this.screenings = screenings;
        });
        this.initScreening();
    }

    initScreening() {
        if (this.selectedScreening) {
            this.newScreening = {
                cinemaRoom: this.selectedScreening.cinemaRoom,
                movie: this.selectedScreening.movie,
                startTime: 0,
                endTime: 0
            };
        }
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

  redirectToAccount() {
    this.router.navigate(['/userdetails']);
  }
    selectScreening() {
      if (!this.cinemaBackendService.isLoggedIn) {
        alert("You are not logged in!");
        this.router.navigate(['/login']);
      } else {
        if (!this.cinemaBackendService.user.address
          || !this.cinemaBackendService.user.phoneNumber
          || !this.cinemaBackendService.user.fullName) {
          this.missingDetails = true;
        }
      }
        this.cinemaBackendService.getAllReservationsToScreening(this.selectedScreening.id).subscribe(reservations => {
            this.reservationList = reservations;
            this.reservations = {};
            reservations.forEach(reservation => {
                if (this.reservations[reservation.row] == null)
                    this.reservations[reservation.row] = {};
                this.reservations[reservation.row][reservation.col] = true;
            });
        });
        this.initScreening();
    }

    reserve(row: number, col: number) {
        let reservation: Reservation = null;
        reservation = this.reservationList.find(reservation => reservation.row == row && reservation.col == col);
        if (reservation) {
            if (this.isAdmin) {
                this.cinemaBackendService.deleteReservation(reservation.id).subscribe(() => {
                    this.selectScreening();
                }, err => {
                    alert("Internal server error.");
                });
            }
        }
        else {
            this.cinemaBackendService.createReservation({ owner: this.cinemaBackendService.user, screening: this.selectedScreening, row: row, col: col }).subscribe(() => {
                this.selectScreening();
            }, err => {
                alert("Internal server error.");
            });
        }
    }

    addScreening() {
        this.newScreening.startTime = new Date(this.startTime).valueOf();
        this.newScreening.endTime = new Date(this.endTime).valueOf();
        if (isNaN(this.newScreening.startTime)) {
            alert("Start time is missing or the given format is wrong.")
            return;
        }
        if (isNaN(this.newScreening.endTime)) {
            alert("End time is missing or the given format is wrong.")
            return;
        }
        this.cinemaBackendService.createScreening(this.newScreening).subscribe(() => {
            this.startTime = null;
            this.endTime = null;
            alert("Screening creation successfull!");
            this.load();
        })
    }
}
