import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InvoiceHeaderTaxDetailsUpdateComponent } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details-update.component';
import { InvoiceHeaderTaxDetailsService } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details.service';
import { InvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';

describe('Component Tests', () => {
  describe('InvoiceHeaderTaxDetails Management Update Component', () => {
    let comp: InvoiceHeaderTaxDetailsUpdateComponent;
    let fixture: ComponentFixture<InvoiceHeaderTaxDetailsUpdateComponent>;
    let service: InvoiceHeaderTaxDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceHeaderTaxDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvoiceHeaderTaxDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceHeaderTaxDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceHeaderTaxDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvoiceHeaderTaxDetails(123);
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
        const entity = new InvoiceHeaderTaxDetails();
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
