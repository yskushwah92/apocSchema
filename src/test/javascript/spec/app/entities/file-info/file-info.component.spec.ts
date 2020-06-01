import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { FileInfoComponent } from 'app/entities/file-info/file-info.component';
import { FileInfoService } from 'app/entities/file-info/file-info.service';
import { FileInfo } from 'app/shared/model/file-info.model';

describe('Component Tests', () => {
  describe('FileInfo Management Component', () => {
    let comp: FileInfoComponent;
    let fixture: ComponentFixture<FileInfoComponent>;
    let service: FileInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FileInfoComponent],
      })
        .overrideTemplate(FileInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FileInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FileInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FileInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fileInfos && comp.fileInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
