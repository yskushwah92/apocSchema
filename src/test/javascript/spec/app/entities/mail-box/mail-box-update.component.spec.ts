import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { MailBoxUpdateComponent } from 'app/entities/mail-box/mail-box-update.component';
import { MailBoxService } from 'app/entities/mail-box/mail-box.service';
import { MailBox } from 'app/shared/model/mail-box.model';

describe('Component Tests', () => {
  describe('MailBox Management Update Component', () => {
    let comp: MailBoxUpdateComponent;
    let fixture: ComponentFixture<MailBoxUpdateComponent>;
    let service: MailBoxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [MailBoxUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MailBoxUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MailBoxUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MailBoxService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MailBox(123);
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
        const entity = new MailBox();
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
