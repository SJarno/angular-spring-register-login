import { Component, OnInit } from '@angular/core';

import { response } from '../response';
import { ServerRequestsService } from '../services/server-requests.service';


@Component({
  selector: 'app-public-data',
  templateUrl: './public-data.component.html',
  styleUrls: ['./public-data.component.css']
})
export class PublicDataComponent implements OnInit {
  data?:any = {};
  /* error?:any; */
  constructor(private getService: ServerRequestsService) {
    
   }

  ngOnInit(): void {
    /* Toimiva */
    this.getService.getPublicData().subscribe(result => {
      this.data = result;  
    });
    
  }

}
