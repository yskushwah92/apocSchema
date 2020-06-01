import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { MailBoxComponent } from 'app/entities/mail-box/mail-box.component';
import { MailBoxService } from 'app/entities/mail-box/mail-box.service';
import { MailBox } from 'app/shared/model/mail-box.model';

describe('Component Tests', () => {
  describe('MailBox Management Component', () => {
    let comp: MailBoxComponent;
    let fixture: ComponentFixture<MailBoxComponent>;
    let service: MailBoxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [MailBoxComponent],
      })
        .overrideTemplate(MailBoxComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MailBoxComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MailBoxService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MailBox(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mailBoxes && comp.mailBoxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
