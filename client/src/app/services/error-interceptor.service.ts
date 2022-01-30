import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor{

  constructor() { }
  handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occured: '
    
    if (error.status === 0 ) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occured:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      if (error.status === 401) {
        /* window.alert("Wrong credentials"); */
        console.error("Status on 401, ei käyttöoikeuksia kohteeseen.");
      }
      errorMessage += error.error;
      console.error(
        `Backend returned error ${error.status}, runko: `, error.error);
    }
    // display error on browser:
    //window.alert(errorMessage);
    console.error(errorMessage);
    // Return an observable with a user-facing error message.
    return throwError(() =>
      errorMessage);

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        catchError(this.handleError))

  };
}
