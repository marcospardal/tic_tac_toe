import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Auth } from '../../services/auth';
import { Router } from '@angular/router';
import { CustomValidators } from '../../validators/CustomValidator';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'register-page',
  templateUrl: './register.html',
  standalone: true,
  styleUrl: './register.sass',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
})
export class RegisterPage {
  private _snackBar = inject(MatSnackBar);

  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: Auth, private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    const user = navigation?.extras.state?.['username'];

    this.registerForm = this.formBuilder.group(
      {
        username: [user || '', Validators.required],
        password: ['', Validators.required],
        confirmPassword: ['', Validators.required],
      },
      {
        validators: [
          CustomValidators.validUsername(),
          CustomValidators.passwordMatch(),
          CustomValidators.validPassword(),
        ],
      }
    );
  }

  onSubmit() {
    console.log(this.registerForm.errors);
    if (this.registerForm.valid) {
      const { username, password } = this.registerForm.value;
      this.authService.register(username, password).subscribe({
        next: (res) => console.log('new user created', res),
        error: (error) => this.openSnackbar(error.error),
      });
    }
  }

  openSnackbar(message: string) {
    this._snackBar.open(message, 'Close', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
    });
  }
}
