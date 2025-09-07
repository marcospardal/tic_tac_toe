import { Component, inject } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Auth } from '../../services/auth';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

type ActionTypes = 'Register new user' | 'Close';

@Component({
  selector: 'login-page',
  templateUrl: './login.html',
  standalone: true,
  styleUrl: './login.sass',
  imports: [ReactiveFormsModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule],
})
export class LoginPage {
  private _snackBar = inject(MatSnackBar);

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: Auth, private router: Router) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.login(username, password).subscribe({
        next: (res) => console.log('login done', res),
        error: (error) => this.onLoginError(error.error),
      });
    }
  }

  private onLoginError(error: string) {
    let action: ActionTypes = 'Close';
    if (error.includes('not found')) {
      action = 'Register new user';
    }

    this.openSnackbar(error, action);
  }

  openSnackbar(message: string, action: ActionTypes) {
    const snackBarRef = this._snackBar.open(message, action, {
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });

    snackBarRef.onAction().subscribe(() => {
      if (action === 'Register new user') {
        this.router.navigate(['/register'], { state: { username: this.loginForm.value.username } });
      }
    });
  }
}
