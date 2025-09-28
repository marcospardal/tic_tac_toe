import { Component } from '@angular/core';
import { Auth } from '../../services/auth';
import { MatButtonModule } from '@angular/material/button';
import { MatCard } from '@angular/material/card';

@Component({
  selector: 'home-page',
  templateUrl: './home.html',
  standalone: true,
  styleUrl: './home.sass',
  imports: [MatButtonModule, MatCard],
})
export class HomePage {
  constructor(private auth: Auth) {
    console.log('token', auth.getToken());
  }
}
