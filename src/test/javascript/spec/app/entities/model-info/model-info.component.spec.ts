import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ModelInfoComponent } from 'app/entities/model-info/model-info.component';
import { ModelInfoService } from 'app/entities/model-info/model-info.service';
import { ModelInfo } from 'app/shared/model/model-info.model';

describe('Component Tests', () => {
  describe('ModelInfo Management Component', () => {
    let comp: ModelInfoComponent;
    let fixture: ComponentFixture<ModelInfoComponent>;
    let service: ModelInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ModelInfoComponent],
      })
        .overrideTemplate(ModelInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModelInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModelInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModelInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modelInfos && comp.modelInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
