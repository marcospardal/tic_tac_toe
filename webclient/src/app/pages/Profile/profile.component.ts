import { Component } from "@angular/core";
import { ProfileService } from "../../services/profile";
import { PlayerSummary } from "../../models/player-sumary.model";
import { MatCard } from '@angular/material/card';

@Component({
  selector: 'profile-page',
  templateUrl: './profile.html',
  standalone: true,
  styleUrl: './profile.sass',
  imports: [MatCard],
})
export class ProfilePage {
  protected playerSummary: PlayerSummary | undefined = undefined;

  constructor(private profileService: ProfileService) {
    
  }

  ngOnInit() {
    this.profileService.getPlayerSummay().subscribe({
      next: (res) => {
        this.playerSummary = res
      }
    })
  }
}