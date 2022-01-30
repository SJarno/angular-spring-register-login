import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  credentials = { 'username': '', 'password': '' };
  errorLogin: boolean = false;
  errorLoginMessage?: string;
  successLogin?: boolean;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.successLogin = this.authService.authenticated;
    this.errorLoginMessage = this.authService.errorMessage;
  }
  /* The original login, according to a tutorial: */
  /* login() {
    this.authService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/profile');

    });
    return false;
  } */
  /* display message, but lacking on redirect etc */
  login() {
    this.authService.authenticate(this.credentials, "").subscribe({
      next: (result: any) => {
        if (result['name']) {
          this.authService.authenticated = true;
          this.authService.username = result['name'];
          this.authService.authorities = result['authorities'];
          this.successLogin = this.authService.authenticated;
          this.errorLoginMessage = "";
          this.router.navigateByUrl('/profile');

          console.log("On kirjautunut: "+this.successLogin);

        }

      },
      error: (e: any) => { this.errorLoginMessage = `Wrong credentials: ${e}` }
    });

  }


}
