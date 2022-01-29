import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }
  authenticated() { return this.auth.authenticated; }
  userRole(role: string) { return this.auth.listContainsRole(role); }
  getUsername() { return this.auth.username; }
  getRoles() {
    return this.auth.authorities;
  }

}
