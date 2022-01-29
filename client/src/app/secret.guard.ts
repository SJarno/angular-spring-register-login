import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SecretGuard implements CanActivate, CanActivateChild {
  constructor(private auth: AuthService, private router: Router) {

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const url: string = state.url;
    return this.checkAuthentication(url);
  }
  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const url: string = state.url;
    return this.checkAuthentication(url);
  }
  checkAuthentication(url: string): true | UrlTree {
    /* return this.auth.authenticated && (this.auth.listContainsRole("ROLE_SECRET") || this.auth.listContainsRole("ROLE_ADMIN")); */
    if (this.auth.authenticated && (this.auth.listContainsRole("ROLE_SECRET") || this.auth.listContainsRole("ROLE_ADMIN"))) {
      
      return true;
    }
    /* redirects to root if failure */
    return this.router.parseUrl("/");
  }

}
