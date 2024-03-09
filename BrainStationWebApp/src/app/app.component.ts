import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'BSWebApp';
  showNavbar = true;

  constructor(private router: Router, public authService: AuthService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showNavbar = !this.router.url.includes('login');
      }
    });
  }

}
