import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CommunicationConfigurationDetailComponent } from 'app/entities/communication-configuration/communication-configuration-detail.component';
import { CommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

describe('Component Tests', () => {
  describe('CommunicationConfiguration Management Detail Component', () => {
    let comp: CommunicationConfigurationDetailComponent;
    let fixture: ComponentFixture<CommunicationConfigurationDetailComponent>;
    const route = ({ data: of({ communicationConfiguration: new CommunicationConfiguration(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CommunicationConfigurationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommunicationConfigurationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommunicationConfigurationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load communicationConfiguration on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.communicationConfiguration).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
