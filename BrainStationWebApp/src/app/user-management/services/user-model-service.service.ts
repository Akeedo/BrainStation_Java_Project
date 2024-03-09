import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { NgForm, UntypedFormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UserModelServiceService {
  user: User;
  userForm : UntypedFormGroup;

  constructor() { }
  
  setDefaultUser(){
    this.user = new User();
  }

  getValidationMessage(controlName: string, form: NgForm): string | null {
    const control = form.controls[controlName];
    if (control && control.touched && control.invalid) {
      // Using type assertion to safely access properties
      if (control.errors['required']) {
        return 'This field is required.';
      }
      if (control.errors['minlength']) {
        // Using the 'as' keyword for type assertion
        const minLengthError = control.errors['minlength'] as {requiredLength: number};
        return `Minimum length is ${minLengthError.requiredLength} characters.`;
      }
      if (control.errors['maxlength']) {
        // Similarly for maxlength
        const maxLengthError = control.errors['maxlength'] as {requiredLength: number};
        return `Maximum length is ${maxLengthError.requiredLength} characters.`;
      }
      if (control.errors['pattern']) {
        return 'Please enter a valid email address.';
      }
    }
    return null;
  }

}
