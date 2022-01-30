import { Component, OnInit } from '@angular/core';
import { ServerRequestsService } from '../services/server-requests.service';
import { UserAccount } from '../user-account';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userAccount!: UserAccount;
  submitted: boolean = false;
  errorMessage!: string;

  constructor(private dataService: ServerRequestsService) {
    this.userAccount = new UserAccount();
   }

  ngOnInit(): void {
  }
  createUser() {
    /* console.log(this.userAccount); */
    this.dataService.createNewUser(this.userAccount).subscribe({
      next: (res) => {
        this.submitted = true;
        this.errorMessage = '';
        console.log(res);
      },
      
      error: (e) => {console.error(e), this.errorMessage = e}
    });
  }

}
