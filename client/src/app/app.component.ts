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
      this.router.navigateByUrl('/');
    })).subscribe();
  }
  /* Ylätasolla halutaan tarkistaa onko käyttäjä kirjautunut:  */
  authenticated() { return this.auth.authenticated; }
}
