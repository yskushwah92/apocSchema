import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CommunicationConfigurationUpdateComponent } from 'app/entities/communication-configuration/communication-configuration-update.component';
import { CommunicationConfigurationService } from 'app/entities/communication-configuration/communication-configuration.service';
import { CommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

describe('Component Tests', () => {
  describe('CommunicationConfiguration Management Update Component', () => {
    let comp: CommunicationConfigurationUpdateComponent;
    let fixture: ComponentFixture<CommunicationConfigurationUpdateComponent>;
    let service: CommunicationConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CommunicationConfigurationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommunicationConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommunicationConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommunicationConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommunicationConfiguration(123);
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
        const entity = new CommunicationConfiguration();
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
