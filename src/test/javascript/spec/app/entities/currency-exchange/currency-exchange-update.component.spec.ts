import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CurrencyExchangeUpdateComponent } from 'app/entities/currency-exchange/currency-exchange-update.component';
import { CurrencyExchangeService } from 'app/entities/currency-exchange/currency-exchange.service';
import { CurrencyExchange } from 'app/shared/model/currency-exchange.model';

describe('Component Tests', () => {
  describe('CurrencyExchange Management Update Component', () => {
    let comp: CurrencyExchangeUpdateComponent;
    let fixture: ComponentFixture<CurrencyExchangeUpdateComponent>;
    let service: CurrencyExchangeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CurrencyExchangeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CurrencyExchangeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CurrencyExchangeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CurrencyExchangeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CurrencyExchange(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CurrencyExchange();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
