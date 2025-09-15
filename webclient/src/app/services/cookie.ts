import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Cookie {
  constructor() {}

  get(name: string): string | null {
    const nameEQ = name + '=';
    const cookies = decodeURIComponent(document.cookie).split(';');

    for (let cookie of cookies) {
      cookie = cookie.trim();

      if (cookie.startsWith(nameEQ)) {
        return cookie.substring(nameEQ.length);
      }
    }

    return null;
  }

  set(name: string, value: string, days: number = 1): void {
    const expires = new Date(Date.now() + days * 864e5).toUTCString();
    document.cookie = `${name}=${value}; expires=${expires}; path=/`;
  }
}
