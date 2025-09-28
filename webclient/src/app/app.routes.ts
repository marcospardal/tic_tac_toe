import { Routes } from '@angular/router';
import { LoginPage } from './pages/Login/login.component';
import { RegisterPage } from './pages/Register/register.component';
import { HomePage } from './pages/Home/home.component';
import { AuthGuard } from './auth-guard';
import { ProfilePage } from './pages/Profile/profile.component';

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
  {
    path: '',
    component: HomePage,
    canActivate: [AuthGuard],
  },
  {
    path: 'profile',
    component: ProfilePage,
    canActivate: [AuthGuard]
  },
  { path: '**', redirectTo: '' },
];
