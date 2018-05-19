import { Injectable, Component } from '@angular/core';
import {CinemaBackendService } from '../../services/cinemaBackend.service';
import { User } from '../../model/cinema.model';
import { Router } from '@angular/router';

@Component({
    selector: 'registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css']
})
@Injectable()
export class RegistrationComponent {
    title = 'registration';
    user: User = {
        email: "",
        password: "",
        username: "",
        role: "",
        fullName: "",
        phoneNumber: "",
        age: 0,
        address: ""
    };


    passwordConfirm: string;
    pwConf: string;
    regSuccessful: boolean = false;
    codeSuccessful: boolean = false;

    constructor(private cinemaBackendService: CinemaBackendService, private router: Router) {

    }

    register() {
        if (this.passwordConfirm != this.user.password) {
            alert("The passwords doesn't match!");
            return;
        }
        this.cinemaBackendService.register(this.user).subscribe(response => {
            this.regSuccessful = true;
            this.getConfCode(this.user.email).subscribe(response => {
              this.passwordConfirm = response['code'];
            }, err => { });
            //this.router.navigate(['/login']);
        }, (err) => {alert("Username already exists!") });
    }

    checkCode() {
      if (this.pwConf === this.passwordConfirm) {
        this.router.navigate(['/login']);
      } else {
        alert("Codes are not matching!");
      }
    }

    getConfCode(email) {
      return this.cinemaBackendService.getConfirmationCode(email);
    }
}
