import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { FileInfoDetailComponent } from 'app/entities/file-info/file-info-detail.component';
import { FileInfo } from 'app/shared/model/file-info.model';

describe('Component Tests', () => {
  describe('FileInfo Management Detail Component', () => {
    let comp: FileInfoDetailComponent;
    let fixture: ComponentFixture<FileInfoDetailComponent>;
    const route = ({ data: of({ fileInfo: new FileInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FileInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FileInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FileInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fileInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fileInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
