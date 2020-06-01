import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { NotificationInfoUpdateComponent } from 'app/entities/notification-info/notification-info-update.component';
import { NotificationInfoService } from 'app/entities/notification-info/notification-info.service';
import { NotificationInfo } from 'app/shared/model/notification-info.model';

describe('Component Tests', () => {
  describe('NotificationInfo Management Update Component', () => {
    let comp: NotificationInfoUpdateComponent;
    let fixture: ComponentFixture<NotificationInfoUpdateComponent>;
    let service: NotificationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [NotificationInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NotificationInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotificationInfo(123);
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
        const entity = new NotificationInfo();
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
