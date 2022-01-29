import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PublicDataComponent } from './public-data/public-data.component';
import { RegisterComponent } from './register/register.component';
import { SecretComponent } from './secret/secret.component';
import { UserDataComponent } from './user-data/user-data.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'public', component: PublicDataComponent},
  { path: 'secret', component: SecretComponent},
  { path: 'admin', component: AdminComponent},
  { path: 'profile', component: UserDataComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'error', component: PageNotFoundComponent},
  { path: '**', redirectTo: 'error', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
