import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { MailBoxDetailComponent } from 'app/entities/mail-box/mail-box-detail.component';
import { MailBox } from 'app/shared/model/mail-box.model';

describe('Component Tests', () => {
  describe('MailBox Management Detail Component', () => {
    let comp: MailBoxDetailComponent;
    let fixture: ComponentFixture<MailBoxDetailComponent>;
    const route = ({ data: of({ mailBox: new MailBox(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [MailBoxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MailBoxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MailBoxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load mailBox on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mailBox).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
