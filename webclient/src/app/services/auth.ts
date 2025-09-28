import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/auth.model';
import { environment } from '../../environments/environment';
import { Cookie } from './cookie';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly TOKEN_KEY = 'auth_token';
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private cookieService: Cookie) {}

  login(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, {
      username,
      userPassword: password,
    });
  }

  register(username: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, {
      username,
      userPassword: password,
    });
  }

  isAuthenticated(): boolean {
    const token = this.cookieService.get(this.TOKEN_KEY);
    return !!token;
  }

  getToken(): string | null {
    const userInfo: AuthResponse = JSON.parse(this.cookieService.get(this.TOKEN_KEY) || '');
    return userInfo.accessToken;
  }

  setToken(token: string): void {
    this.cookieService.set(this.TOKEN_KEY, token, 1);
  }
}
