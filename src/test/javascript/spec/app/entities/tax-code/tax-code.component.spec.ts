import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { TaxCodeComponent } from 'app/entities/tax-code/tax-code.component';
import { TaxCodeService } from 'app/entities/tax-code/tax-code.service';
import { TaxCode } from 'app/shared/model/tax-code.model';

describe('Component Tests', () => {
  describe('TaxCode Management Component', () => {
    let comp: TaxCodeComponent;
    let fixture: ComponentFixture<TaxCodeComponent>;
    let service: TaxCodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [TaxCodeComponent],
      })
        .overrideTemplate(TaxCodeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TaxCodeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TaxCodeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TaxCode(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.taxCodes && comp.taxCodes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
