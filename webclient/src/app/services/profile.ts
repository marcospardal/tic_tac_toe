import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Auth } from './auth';
import { environment } from '../../environments/environment';
import { PlayerSummary } from '@models/player-sumary.model';
import { PlayerGameHistory } from '@models/player-history.model';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private apiUrl = environment.apiUrl;
  private headers;

  constructor(private http: HttpClient, private authService: Auth) {
    this.headers = { Authorization: 'Bearer ' + this.authService.getToken() };
  }

  getPlayerSummay(): Observable<PlayerSummary> {
    return this.http.get<PlayerSummary>(`${this.apiUrl}/player-summary`, {
      headers: this.headers,
    });
  }

  getPlayerGameHistory(): Observable<PlayerGameHistory[]> {
    return this.http.get<PlayerGameHistory[]>(`${this.apiUrl}/player-history`, {
      headers: this.headers,
    });
  }
}
