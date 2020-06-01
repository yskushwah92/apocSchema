import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { FileSourceDetailsComponent } from 'app/entities/file-source-details/file-source-details.component';
import { FileSourceDetailsService } from 'app/entities/file-source-details/file-source-details.service';
import { FileSourceDetails } from 'app/shared/model/file-source-details.model';

describe('Component Tests', () => {
  describe('FileSourceDetails Management Component', () => {
    let comp: FileSourceDetailsComponent;
    let fixture: ComponentFixture<FileSourceDetailsComponent>;
    let service: FileSourceDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FileSourceDetailsComponent],
      })
        .overrideTemplate(FileSourceDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FileSourceDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FileSourceDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FileSourceDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fileSourceDetails && comp.fileSourceDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
