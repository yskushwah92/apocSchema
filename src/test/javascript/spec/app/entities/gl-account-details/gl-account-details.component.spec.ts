import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { GLAccountDetailsComponent } from 'app/entities/gl-account-details/gl-account-details.component';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';
import { GLAccountDetails } from 'app/shared/model/gl-account-details.model';

describe('Component Tests', () => {
  describe('GLAccountDetails Management Component', () => {
    let comp: GLAccountDetailsComponent;
    let fixture: ComponentFixture<GLAccountDetailsComponent>;
    let service: GLAccountDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GLAccountDetailsComponent],
      })
        .overrideTemplate(GLAccountDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GLAccountDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GLAccountDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GLAccountDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.gLAccountDetails && comp.gLAccountDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
