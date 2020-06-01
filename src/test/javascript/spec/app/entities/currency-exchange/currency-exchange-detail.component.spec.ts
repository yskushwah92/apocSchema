import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CurrencyExchangeDetailComponent } from 'app/entities/currency-exchange/currency-exchange-detail.component';
import { CurrencyExchange } from 'app/shared/model/currency-exchange.model';

describe('Component Tests', () => {
  describe('CurrencyExchange Management Detail Component', () => {
    let comp: CurrencyExchangeDetailComponent;
    let fixture: ComponentFixture<CurrencyExchangeDetailComponent>;
    const route = ({ data: of({ currencyExchange: new CurrencyExchange(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CurrencyExchangeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CurrencyExchangeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CurrencyExchangeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load currencyExchange on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.currencyExchange).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
