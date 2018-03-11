import { TestBed, inject } from '@angular/core/testing';

import { GaleryService } from './galery.service';

describe('GaleryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GaleryService]
    });
  });

  it('should be created', inject([GaleryService], (service: GaleryService) => {
    expect(service).toBeTruthy();
  }));
});
