import { Component } from '@angular/core';
import { ProfileService } from '@services/profile';
import { PlayerSummary } from '@models/player-sumary.model';
import { MatCard } from '@angular/material/card';
import { PlayerGameHistory } from '@models/player-history.model';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'profile-page',
  templateUrl: './profile.html',
  standalone: true,
  styleUrl: './profile.sass',
  imports: [MatCard, MatTableModule],
})
export class ProfilePage {
  protected playerSummary: PlayerSummary | undefined = undefined;
  protected playerGameHistory: PlayerGameHistory[] = [];

  gamesTableColumns: string[] = ['opponent', 'winner', 'duration'];

  constructor(private profileService: ProfileService) {}

  ngOnInit() {
    this.profileService.getPlayerSummay().subscribe({
      next: (res) => {
        this.playerSummary = res;
      },
    });
    this.profileService.getPlayerGameHistory().subscribe({
      next: (res) => {
        this.playerGameHistory = res;
      },
    });
  }
}
