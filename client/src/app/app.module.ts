import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NavigationComponent } from './navigation/navigation.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PublicDataComponent } from './public-data/public-data.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { ErrorInterceptorService } from './services/error-interceptor.service';
import { LoginInterceptorService } from './services/login-interceptor.service';

import { SecretComponent } from './secret/secret.component';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavigationComponent,
    PageNotFoundComponent,
    PublicDataComponent,
    SecretComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: LoginInterceptorService, multi: true}
    ,
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true}
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
