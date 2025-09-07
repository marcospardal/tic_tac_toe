import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export class CustomValidators {
  static validUsername(): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const username = formGroup.get('username')?.value;

      if (username.length == 0 || username.length > 20) {
        formGroup.get('username')?.setErrors({ usernameIsTooLong: true });
        return { usernameIsTooLong: true };
      } else {
        this.removeError('username', 'usernameIsTooLong', formGroup);

        return null;
      }
    };
  }

  static passwordMatch(): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const password = formGroup.get('password')?.value;
      const confirmPassword = formGroup.get('confirmPassword')?.value;

      if (password !== confirmPassword || password.length === 0) {
        formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
        return { passwordMismatch: true };
      } else {
        this.removeError('confirmPassword', 'passwordMismatch', formGroup);

        return null;
      }
    };
  }

  static validPassword(): ValidatorFn {
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const password = formGroup.get('password')?.value;
      const numberRegex = /[0-9]/;

      if (numberRegex.test(password)) {
        this.removeError('password', 'passwordDontContainNumbers', formGroup);

        return null;
      } else {
        formGroup.get('password')?.setErrors({ passwordDontContainNumbers: true });
        return { passwordDontContainNumbers: true };
      }
    };
  }

  static removeError(fieldKey: string, errorKey: string, formGroup: AbstractControl) {
    const errors = formGroup.get(fieldKey)?.errors;

    if (errors) {
      delete errors[errorKey];

      if (!Object.keys(errors).length) {
        formGroup.get(fieldKey)?.setErrors(null);
      } else {
        formGroup.get(fieldKey)?.setErrors(errors);
      }
    }
  }
}
