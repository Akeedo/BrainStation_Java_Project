import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';

const routes: Routes = [
  // { path: '', redirectTo: 'auth', pathMatch: 'full' },
  
{ path: '', redirectTo: 'user-management', pathMatch: 'full' },
  {
    path: 'user-management',
    loadChildren: () =>
      import('./user-management/user-management.module').then((m) => m.UserManagementModule),
  }
  ,
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
