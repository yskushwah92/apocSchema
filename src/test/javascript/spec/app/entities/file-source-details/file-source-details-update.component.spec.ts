import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { FileSourceDetailsUpdateComponent } from 'app/entities/file-source-details/file-source-details-update.component';
import { FileSourceDetailsService } from 'app/entities/file-source-details/file-source-details.service';
import { FileSourceDetails } from 'app/shared/model/file-source-details.model';

describe('Component Tests', () => {
  describe('FileSourceDetails Management Update Component', () => {
    let comp: FileSourceDetailsUpdateComponent;
    let fixture: ComponentFixture<FileSourceDetailsUpdateComponent>;
    let service: FileSourceDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FileSourceDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FileSourceDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FileSourceDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FileSourceDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FileSourceDetails(123);
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
        const entity = new FileSourceDetails();
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
