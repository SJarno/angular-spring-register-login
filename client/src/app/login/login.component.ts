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
  errorLogin?:string;
  successLogin?:string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  login() {
    this.authService.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/profile');
  
    });
    return false;
  }


}
