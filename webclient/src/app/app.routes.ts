import { Routes } from '@angular/router';
import { LoginPage } from './pages/Login/login.component';
import { RegisterPage } from './pages/Register/register.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginPage,
    title: 'Login page',
  },
  {
    path: 'register',
    component: RegisterPage,
    title: 'Register new user',
  },
];
