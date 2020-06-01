import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { CurrencyExchangeComponent } from 'app/entities/currency-exchange/currency-exchange.component';
import { CurrencyExchangeService } from 'app/entities/currency-exchange/currency-exchange.service';
import { CurrencyExchange } from 'app/shared/model/currency-exchange.model';

describe('Component Tests', () => {
  describe('CurrencyExchange Management Component', () => {
    let comp: CurrencyExchangeComponent;
    let fixture: ComponentFixture<CurrencyExchangeComponent>;
    let service: CurrencyExchangeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CurrencyExchangeComponent],
      })
        .overrideTemplate(CurrencyExchangeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CurrencyExchangeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrencyExchangeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CurrencyExchange(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.currencyExchanges && comp.currencyExchanges[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
