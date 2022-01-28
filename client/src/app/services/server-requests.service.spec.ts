import { TestBed } from '@angular/core/testing';

import { ServerRequestsService } from './server-requests.service';

describe('ServerRequestsService', () => {
  let service: ServerRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServerRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
