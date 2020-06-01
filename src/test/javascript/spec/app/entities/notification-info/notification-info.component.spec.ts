import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { NotificationInfoComponent } from 'app/entities/notification-info/notification-info.component';
import { NotificationInfoService } from 'app/entities/notification-info/notification-info.service';
import { NotificationInfo } from 'app/shared/model/notification-info.model';

describe('Component Tests', () => {
  describe('NotificationInfo Management Component', () => {
    let comp: NotificationInfoComponent;
    let fixture: ComponentFixture<NotificationInfoComponent>;
    let service: NotificationInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [NotificationInfoComponent],
      })
        .overrideTemplate(NotificationInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotificationInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotificationInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NotificationInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.notificationInfos && comp.notificationInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
