import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ContactDetailsComponent } from 'app/entities/contact-details/contact-details.component';
import { ContactDetailsService } from 'app/entities/contact-details/contact-details.service';
import { ContactDetails } from 'app/shared/model/contact-details.model';

describe('Component Tests', () => {
  describe('ContactDetails Management Component', () => {
    let comp: ContactDetailsComponent;
    let fixture: ComponentFixture<ContactDetailsComponent>;
    let service: ContactDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactDetailsComponent],
      })
        .overrideTemplate(ContactDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContactDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contactDetails && comp.contactDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
