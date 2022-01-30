import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  authenticated?: boolean;

  constructor(private auth: AuthService, private http: HttpClient, private router: Router) {
    this.authenticated = this.auth.authenticated;
   }

  ngOnInit(): void {
    this.authenticated = this.auth.authenticated;
  }
  isAuthenticated() {
    return this.auth.authenticated;
  }
  logout() {
    this.http.post(environment.baseUrl+'logout', {}).pipe( finalize(() => {
      this.auth.authenticated = false;
      this.auth.authorities = [];
      this.auth.username = '';
      this.router.navigateByUrl('/');
    })).subscribe();
  }

}
