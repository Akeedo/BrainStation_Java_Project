import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { LoginComponent } from '../auth/login/login.component';
import { AuthGuard } from '../auth/guards/auth.guard';

const routes: Routes = [
 
  // Protected routes
  {
    path: '',
    canActivateChild: [AuthGuard], // Applying AuthGuard to all child routes
    children: [
      {
        path: '',
        component: DashboardComponent,
      },
      {
        path: 'user-registration',
        component: UserRegistrationComponent,
        data: { expectedUserName: 'admin' }, // Example of passing data to the guard
      },
      // Add more protected routes here
    ],
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserManagementRoutingModule { }
