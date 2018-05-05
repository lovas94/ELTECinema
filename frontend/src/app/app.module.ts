import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }   from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './containers/login/login.component';
import { RegistrationComponent } from './containers/registration/registration.component';
import { ReservationComponent } from './containers/reservation/reservation.component';
import { ScreeningsComponent } from './containers/screenings/screenings.component';

import { CinemaBackendService } from './services/cinemaBackend.service';

import { AgeLimitPipe } from './pipes/ageLimit.pipe'
import {HomeComponent} from "./containers/home/home.component";
import {UserDetailsComponent} from "./containers/userdetails/userdetails.component";
import {BookingComponent} from "./containers/bookings/bookings.component";

const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'register',
        component: RegistrationComponent
    },
    {
        path: 'reservation/:id',
        component: ReservationComponent
    },
    {
        path: 'screenings',
        component: ScreeningsComponent
    },
    {
        path: 'userdetails',
        component: UserDetailsComponent
    },
    {
        path: 'bookings',
        component: BookingComponent
    }
];

@NgModule({
    declarations: [
        LoginComponent,
        RegistrationComponent,
        ReservationComponent,
        ScreeningsComponent,
        AgeLimitPipe,
        AppComponent,
        HomeComponent,
        UserDetailsComponent,
        BookingComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes),
        FormsModule,
        HttpClientModule
    ],
    providers: [CinemaBackendService, CookieService],
    bootstrap: [AppComponent]
})
export class AppModule { }
