import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { GRNDetailsComponent } from 'app/entities/grn-details/grn-details.component';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';
import { GRNDetails } from 'app/shared/model/grn-details.model';

describe('Component Tests', () => {
  describe('GRNDetails Management Component', () => {
    let comp: GRNDetailsComponent;
    let fixture: ComponentFixture<GRNDetailsComponent>;
    let service: GRNDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GRNDetailsComponent],
      })
        .overrideTemplate(GRNDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GRNDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GRNDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GRNDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gRNDetails && comp.gRNDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
