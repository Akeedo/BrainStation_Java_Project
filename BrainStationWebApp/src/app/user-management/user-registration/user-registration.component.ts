import { Component, OnInit } from '@angular/core';
import { UserModelServiceService } from '../services/user-model-service.service';
import { NgForm } from '@angular/forms';
import { UserDataServiceService } from '../services/user-data-service.service';
import { MessagesModule } from 'primeng/messages';
import { Message } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss'],
  providers: [MessageService]
})
export class UserRegistrationComponent implements OnInit{

  emptyUserSave: boolean = false;
  
  messages: Message[] = [];

  userRegistrationForm: NgForm;

  constructor(
    public modelSvc: UserModelServiceService,
    private dataSvc: UserDataServiceService,
    public messageService: MessageService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.modelSvc.setDefaultUser();
  }

  // Inside your component class

  onSave(userForm: NgForm) {

    if (userForm.invalid) {
      this.emptyUserSave = true;
      this.messages = [
        { severity: 'error', summary: 'Error', detail: 'Please fill in all required fields' }
      ];
      return;
    }
 
    
    this.dataSvc.saveUser(this.modelSvc.user).subscribe({
      next: (data) => {
        // This function runs when the Observable emits a value (i.e., the request succeeds)
        this.messageService.add({severity: 'success', summary:  'Heading', detail: data.userName });
       
          this.router.navigate(['/user-management']);
     
      },
      error: (e) => {
        this.emptyUserSave = true;
        // This function runs when the Observable emits an error (i.e., the request fails)
        this.messages = [
          { severity: 'error', summary: 'Error', detail: e.error.message }
        ];
      },
      complete: () => {
        // This function runs when the Observable completes (i.e., no more values or errors will be emitted)
        console.log('Request completed');
      }
    });
  } 
}
