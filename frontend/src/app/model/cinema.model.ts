import { Data } from "@angular/router/src/config";

export interface User {
    id?: number;
    username: string;
    password: string;
    email: string;
    role: string;
    fullName: string;
    address: string;
    phoneNumber: string;
    age: number;
}

export interface LoginResponse {
    success: boolean;
}

export interface Movie {
    id?: number;
    title: string;
    dubbed: boolean;
    director: string;
    story: string;
    length: number;
    ageLimit: string;
    ticketSold: number;
}

export interface CinemaRoom {
    id: number;
    name: string;
    rows: number;
    columns: number;
}

export interface Screening {
    id?: number;
    movie: Movie;
    cinemaRoom: CinemaRoom;
    startTime: number;
    endTime: number;
}

export interface Reservation {
    id?: number;
    owner: User;
    screening: Screening;
    row: number;
    col: number;
}
