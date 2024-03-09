import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
   // Public routes
   
   { path: '', redirectTo: 'login', pathMatch: 'full' },

      {
        path: 'login',
        component: LoginComponent,
      },
      // Other public routes like 'register', 'forgot-password', etc.

  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
