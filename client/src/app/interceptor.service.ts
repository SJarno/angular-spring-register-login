import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  errorMessage: string | undefined;
  constructor(

  ) { }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Jotain meni vikaan, yritä myöhemmin uudelleen.'
    
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('Tapahtui virhe:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend palautti koodin ${error.status}, runko: `, error.error);
    }
    // display error on browser:
    window.alert(errorMessage);
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
