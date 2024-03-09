import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appRedOnEmpty]' // Adjust the selector as needed
})
export class RedOnEmptyDirective {

  constructor(private el: ElementRef) {}

  @HostListener('focus') onFocus() {
    this.removeRedBorder(); // Ensure no red border on focus
  }

  @HostListener('blur') onBlur() {
    if (!this.el.nativeElement.value) {
     this.addRedBorder(); // Add red border if input is empty on blur
    } else {
      this.removeRedBorder(); // Ensure no red border if input is not empty
    }
  }

  private addRedBorder() {
    this.el.nativeElement.style.border = '1px solid red';
  }

  private removeRedBorder() {
    this.el.nativeElement.style.border = '';
  }
}
