import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InvoiceStatusDetailsUpdateComponent } from 'app/entities/invoice-status-details/invoice-status-details-update.component';
import { InvoiceStatusDetailsService } from 'app/entities/invoice-status-details/invoice-status-details.service';
import { InvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';

describe('Component Tests', () => {
  describe('InvoiceStatusDetails Management Update Component', () => {
    let comp: InvoiceStatusDetailsUpdateComponent;
    let fixture: ComponentFixture<InvoiceStatusDetailsUpdateComponent>;
    let service: InvoiceStatusDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceStatusDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvoiceStatusDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceStatusDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceStatusDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InvoiceStatusDetails(123);
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
        const entity = new InvoiceStatusDetails();
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
