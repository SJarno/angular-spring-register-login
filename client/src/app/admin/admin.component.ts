import { Component, OnInit } from '@angular/core';
import { ServerRequestsService } from '../server-requests.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  data?:any = {};

  constructor(private getService: ServerRequestsService) { }

  ngOnInit(): void {
    this.getService.getAdminData().subscribe(result => {
      this.data = result;
    });
  }

}
