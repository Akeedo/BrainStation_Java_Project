import { Component, OnInit } from '@angular/core';
import { UserModelServiceService } from '../../user-management/services/user-model-service.service';
import { NgForm } from '@angular/forms';
import { UserDataServiceService } from '../../user-management/services/user-data-service.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Store } from '@ngrx/store';
import { Message, MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [MessageService]
})
export class LoginComponent implements OnInit{
  errorMessage: string;
  messages: Message[] = [];
  emptyUserLogin: boolean = false;
 
  constructor(
    private router: Router,
    public modelSvc: UserModelServiceService,
    private authSvc: AuthService,
    public messageService: MessageService,
    ) { }

  ngOnInit(): void {
    this.modelSvc.setDefaultUser();
  }

  onSave(loginForm: NgForm) {
   try{ 
  if (loginForm.invalid) {
      this.emptyUserLogin = true;
      this.errorMessage = 'Please correct the errors and try again.';
      this.messages = [
        { severity: 'error', summary: 'Error', detail: 'Please correct the errors and try again.' }
      ];
      return;
  }
    this.authSvc.onLogin(this.modelSvc.user).subscribe({
        next: (response) => {
          // This function runs when the Observable emits a value (i.e., the request succeeds)
        localStorage.setItem('access_token', response.token);
        localStorage.setItem('refresh_token', response.refreshToken);
        localStorage.setItem('userName', response.user.userName);
        localStorage.setItem('email', response.user.email);
        // This function runs when the Observable emits a value (i.e., the request succeeds)
        this.messageService.add({severity: 'success', summary:  ' Successfully Login', detail: response.user.userName });
          setTimeout(() => {
            this.router.navigate(['/user-management']);
          }, 2000); // Delay for 3 seconds
        },
        error: (e) => {
          // This function runs when the Observable emits an error (i.e., the request fails)
          console.log('Error saving user', e);
          this.errorMessage = 'Login failed. Please check your credentials.';
          this.messages = [
          { severity: 'error', summary: 'Error', detail: e.error.message }
        ];
        },
      });
    } catch (error) {
      console.log('Error saving user', error);
    }
  }
}
