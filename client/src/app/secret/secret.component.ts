import { Component, OnInit } from '@angular/core';
import { ServerRequestsService } from '../services/server-requests.service';

@Component({
  selector: 'app-secret',
  templateUrl: './secret.component.html',
  styleUrls: ['./secret.component.css']
})
export class SecretComponent implements OnInit {
  data?:any = {};

  constructor(private getService: ServerRequestsService) { }

  ngOnInit(): void {
    this.getService.getSecretData().subscribe(result => {
      this.data = result;
    });
  }

}
