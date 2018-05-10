import { Injectable } from '@angular/core';
import { User, Movie, Screening, Reservation } from '../model/cinema.model';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { tap } from "rxjs/operators";
import {CookieService} from "angular2-cookie/services/cookies.service";

const BASE_URL = "http://localhost:8080/";

const httpOptions = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json'}
  )
};

@Injectable()
export class CinemaBackendService {

    isLoggedIn: boolean = false;
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

    user: User = {
      username: "Ismeretlen",
      password: "",
      email: "",
      role: "",
      fullName: "",
      phoneNumber: "",
      age: -1,
      address: ""
    };
    redirectUrl: string;

    constructor(private http: HttpClient, private cookieService: CookieService) {
      if (this.cookieService.get("user") != null) {
        this.isLoggedIn = true;
        this.getByUsername(this.cookieService.get("user")).subscribe(response => {
          this.user = this.userFromJson(response);
        });
      }
    }

    init() {
      this.isLoggedIn = false;
      this.cookieService.remove("user");
      this.user = {
        username: "Ismeretlen",
        password: "",
        email: "",
        role: "",
        fullName: "",
        phoneNumber: "",
        age: -1,
        address: ""
      };
    }

    getNews() {
      return this.getNoParam<string>(BASE_URL + 'api/news/getAll');
    }
    isAdmin() {
        return !!parseInt(localStorage.getItem('isAdminValue'));
    }

    getByUsername(userName: string) {
        console.log(userName);
        return this.getNoParam<User>(BASE_URL+'user/getUserByUsername?username='+userName);
    }
    getLoggedInUser() {
        return this.getNoParam<string>(BASE_URL + 'user/getCurrentUser');
    }

    getConfirmationCode(email) {
      return this.getNoParam<string>(BASE_URL + '/user/generateConfCode?mail='+email);
    }
    updateUser(user: User) {
        return this.postBody<User>(BASE_URL + 'user/update', user);
    }

    login(user: User) {
        localStorage.setItem('isAdminValue', (user.username == "admin") ? "1" : "0");
        this.get<string>(BASE_URL + 'user/login', user);
        return this.post<User>(BASE_URL + 'user/login', user);
    }

    logout(user: User) {
      return this.post<string>(BASE_URL + 'user/logout', user);
    }

    register(user: User) {
        this.get<string>(BASE_URL + 'user/register', user);
        return this.post<string>(BASE_URL + 'user/register', user);
    }

    getMovies() {
        return this.http.get<Movie[]>(BASE_URL + 'api/movies/getall');
    }

    addMovie(movie: Movie) {
        return this.http.post<void>(BASE_URL + 'api/movies/create', movie);
    }

    getScreeningsByMovie(movieId: number) {
        return this.http.get<Screening[]>(BASE_URL + 'api/screenings/getAllByMovie/' + movieId);
    }

    createScreening(screening: Screening) {
        return this.http.post<void>(BASE_URL + 'api/screenings/create', screening);
    }

    getAllReservationsToScreening(screeningId: number) {
        return this.http.get<Reservation[]>(BASE_URL + 'api/reservations/getAllToScreening/' + screeningId);
    }

    createReservation(reservation: Reservation) {
        return this.http.post<void>(BASE_URL + 'api/reservations/create', reservation);
    }

    deleteReservation(reservationId: number) {
        return this.http.delete<void>(BASE_URL + 'api/reservations/delete/' + reservationId);
    }

    getAllReservation() {
        return this.http.get<Reservation[]>(BASE_URL + 'api/reservations/getall');
    }

    getAllReservationToUser(user: User) {
        console.log(BASE_URL+'api/reservations/getAllToUser/'+user.id);
        return this.http.get<Reservation[]>(BASE_URL+'api/reservations/getAllToUser/'+user.id);
    }

    get<TResponse>(action: string, params: any) {
        let url = action + '?' + Object.keys(params).map(key => key + '=' + params[key]).join('&');
        return this.http.get<TResponse>(url);
    }

    getNoParam<TResponse>(action: string) {
        return this.http.get<TResponse>(action);
    }

    postNoParam(action: string, params: any) {
        return this.post(action, params);
    }
    post<TResponse>(action: string, params: any) {
        let url = action + '?' + Object.keys(params).map(key => key + '=' + params[key]).join('&');
        return this.http.post<TResponse>(url, {});
    }

    postBody<TResponse>(action:string, body: any) {
        return this.http.post(action, body);
    }
}
