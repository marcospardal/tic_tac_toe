import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Auth } from './auth';
import { environment } from '../../environments/environment';
import { PlayerSummary } from '../models/player-sumary.model';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private authService: Auth) {}

  getPlayerSummay(): Observable<PlayerSummary> {
    return this.http.get<PlayerSummary>(`${this.apiUrl}/player-summary`, {
      headers: { Authorization: 'Bearer ' + this.authService.getToken() },
    });
  }
}
