import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { FileSourceDetailsDetailComponent } from 'app/entities/file-source-details/file-source-details-detail.component';
import { FileSourceDetails } from 'app/shared/model/file-source-details.model';

describe('Component Tests', () => {
  describe('FileSourceDetails Management Detail Component', () => {
    let comp: FileSourceDetailsDetailComponent;
    let fixture: ComponentFixture<FileSourceDetailsDetailComponent>;
    const route = ({ data: of({ fileSourceDetails: new FileSourceDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FileSourceDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FileSourceDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FileSourceDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fileSourceDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fileSourceDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
