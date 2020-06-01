import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { GLAccountDetailsDetailComponent } from 'app/entities/gl-account-details/gl-account-details-detail.component';
import { GLAccountDetails } from 'app/shared/model/gl-account-details.model';

describe('Component Tests', () => {
  describe('GLAccountDetails Management Detail Component', () => {
    let comp: GLAccountDetailsDetailComponent;
    let fixture: ComponentFixture<GLAccountDetailsDetailComponent>;
    const route = ({ data: of({ gLAccountDetails: new GLAccountDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GLAccountDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GLAccountDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GLAccountDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gLAccountDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gLAccountDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
