import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ModelInfoDetailComponent } from 'app/entities/model-info/model-info-detail.component';
import { ModelInfo } from 'app/shared/model/model-info.model';

describe('Component Tests', () => {
  describe('ModelInfo Management Detail Component', () => {
    let comp: ModelInfoDetailComponent;
    let fixture: ComponentFixture<ModelInfoDetailComponent>;
    const route = ({ data: of({ modelInfo: new ModelInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ModelInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModelInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModelInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modelInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modelInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
