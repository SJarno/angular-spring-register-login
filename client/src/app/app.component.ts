import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { finalize } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular Demo';

  /* Authentication in base app? */
  constructor(private auth: AuthService, private http: HttpClient, private router: Router) {
    this.auth.authenticate(undefined, undefined);
    
  }
  
  logout() {
    this.http.post(environment.baseUrl+'logout', {}).pipe( finalize(() => {
      this.auth.authenticated = false;
      this.auth.authorities = [];
      this.auth.username = '';
      this.router.navigateByUrl('/');
    })).subscribe();
  }
  /* Check on the upper level if user is authenticated, can be mode to a user page if necessary:  */
  authenticated() { return this.auth.authenticated; }
  userRole(role: string) { return this.auth.listContainsRole(role); }
  getUsername() { return this.auth.username; }
  getRoles() {
    return this.auth.authorities;
  }
}
