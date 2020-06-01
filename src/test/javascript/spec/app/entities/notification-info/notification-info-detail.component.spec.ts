import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { NotificationInfoDetailComponent } from 'app/entities/notification-info/notification-info-detail.component';
import { NotificationInfo } from 'app/shared/model/notification-info.model';

describe('Component Tests', () => {
  describe('NotificationInfo Management Detail Component', () => {
    let comp: NotificationInfoDetailComponent;
    let fixture: ComponentFixture<NotificationInfoDetailComponent>;
    const route = ({ data: of({ notificationInfo: new NotificationInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [NotificationInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NotificationInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotificationInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notificationInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notificationInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
