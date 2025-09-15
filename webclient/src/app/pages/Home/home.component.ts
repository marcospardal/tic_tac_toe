import { Component } from "@angular/core";
import { Auth } from "../../services/auth";

@Component({
  selector: 'home-page',
  templateUrl: './home.html',
  standalone: true,
  styleUrl: './home.sass'
})
export class HomePage {
  constructor (private auth: Auth) {
    console.log('token', auth.getToken())
  }

}